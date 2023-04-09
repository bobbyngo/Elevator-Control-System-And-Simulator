package main.java.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.DatagramPacket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import main.java.SimulatorConfiguration;
import main.java.UDPClient;
import main.java.dto.ElevatorGuiData;
import main.java.dto.FloorGuiData;
import main.java.elevator.state.ElevatorStateEnum;

/**
 * Graphical user interface for the elevator control system and simulation.
 * @author Trong Nguyen
 */
public class GUI extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private static int elevatorNum;
	private static int floorNum;
	private JLabel[][] floors;
	private JLabel[][] floorTitles;
	private JLabel[][] elevInfos;
	private UDPClient floorDtoSocket;
	private UDPClient elevatorDtoSocket;
	
	/**
	 * Constructor for the graphical user interface.
	 * @param config
	 */
	public GUI(SimulatorConfiguration config) {
		elevatorNum = config.NUM_ELEVATORS;
		floorNum = config.NUM_FLOORS;
		// initialize sockets
		floorDtoSocket = new UDPClient(config.GUI_FLOOR_DTO_PORT);
		elevatorDtoSocket = new UDPClient(config.GUI_ELEVATOR_DTO_PORT);
	}
	
	/**
	 * Displays the main graphic user interface frame.
	 */
	private void displayGUI() {
		int frameHeight = 165 + 30 * floorNum;
		int frameWidth = 50 + (50 * elevatorNum);
		
		if (elevatorNum % 2 == 0) {//add width for elevator data
			frameWidth += (elevatorNum / 2) * 350;
		}else {
			frameWidth += ((elevatorNum / 2) + 1) * 350;
		}

		frameWidth += 160;
		// Create main frame
		this.setTitle("ELEVATOR-CONTROL-SYSTEM-AND-SIMULATOR");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("./src/main/resources/assets/favicon.png"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, frameWidth, frameHeight + 165);
		this.setPreferredSize(new Dimension(1080, 740));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(true);
		this.setVisible(true);
		// Set main frame layout and constraints
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {frameWidth};
		gridBagLayout.rowHeights = new int[] {frameHeight};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0};
		this.setLayout(gridBagLayout);
		
		JPanel displayPanel = new JPanel();
		displayPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbcDisplayPanel = new GridBagConstraints();
		gbcDisplayPanel.insets = new Insets(0, 0, 5, 0);
		gbcDisplayPanel.anchor = GridBagConstraints.WEST;
		gbcDisplayPanel.fill = GridBagConstraints.VERTICAL;
		gbcDisplayPanel.gridx = 0;
		gbcDisplayPanel.gridy = 0;
		this.add(displayPanel, gbcDisplayPanel);
		
		JScrollPane areaScrollPane = new JScrollPane(displayPanel);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setContentPane(areaScrollPane);
		
		GridBagLayout gblDisplayPanel = new GridBagLayout();
		int columns = 1 + elevatorNum + 1; // adds the floor column, elevator columns, then the data column
		int[] columnWidth = new int[columns];
		for (int i = 0; i < columns; i++) {
			if(i != 1 + elevatorNum) {
				columnWidth[i] = 70;
			}else {
				if (elevatorNum % 2 == 0) { // adds width for elevator data
					columnWidth[i] = (elevatorNum / 2) * 300;
				}else {
					columnWidth[i] = ((elevatorNum / 2) + 1) * 300;
				}
			}
		}
		gblDisplayPanel.columnWidths = columnWidth;
		gblDisplayPanel.rowHeights = new int[] {frameHeight};
		double[] gblColumnWeights = new double[columns];
		for (int i = 0; i < columns; i++) {
			gblColumnWeights[i] = 0.0;
		}
		gblDisplayPanel.columnWeights = gblColumnWeights;
		gblDisplayPanel.rowWeights = new double[]{1.0};
		displayPanel.setLayout(gblDisplayPanel);
		
		
		// Initialize floor panels
		JPanel floorTitlePanel = new JPanel();
		floorTitlePanel.setBackground(UIManager.getColor("Button.background"));
		floorTitlePanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Floors", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		GridBagConstraints gbcFloorTitlePanel = new GridBagConstraints();
		gbcFloorTitlePanel.fill = GridBagConstraints.VERTICAL;
		gbcFloorTitlePanel.insets = new Insets(0, 0, 0, 5);
		gbcFloorTitlePanel.gridx = 0;
		gbcFloorTitlePanel.gridy = 0;
		displayPanel.add(floorTitlePanel, gbcFloorTitlePanel);
		
		GridBagLayout gblFloorTitlePanel = new GridBagLayout();
		gblFloorTitlePanel.columnWidths = new int[] {70};
		int[] tempArr = new int[floorNum];
		// Initialize the temp array for the floor
		for (int j = 0; j < floorNum; j++) {
			tempArr[j] = 30;
		}
		gblFloorTitlePanel.rowHeights = tempArr;
		gblFloorTitlePanel.columnWeights = new double[]{0.0};
		double[] temp = new double[floorNum];
		for (int j = 0; j < floorNum; j++) {
			temp[j] = 1.0;
		}
		gblFloorTitlePanel.rowWeights = temp;
		floorTitlePanel.setLayout(gblFloorTitlePanel);
		
		floorTitles = new JLabel[3][floorNum];
		for(int i = 1; i <= floorNum; i++) {
			floorTitles[0][i - 1] = new JLabel("");
			floorTitles[0][i - 1].setIcon(new ImageIcon("./src/main/resources/assets/inactive-floor-up.png"));
			floorTitles[0][i - 1].setHorizontalAlignment(SwingConstants.LEFT);
			floorTitles[1][i - 1] = new JLabel(Integer.toString(floorNum - i + 1));
			floorTitles[1][i - 1].setHorizontalAlignment(SwingConstants.CENTER);
			floorTitles[2][i - 1] = new JLabel("");
			floorTitles[2][i - 1].setIcon(new ImageIcon("./src/main/resources/assets/inactive-floor-down.png"));
			floorTitles[2][i - 1].setHorizontalAlignment(SwingConstants.RIGHT);
			
			GridBagConstraints gbcFloorTitle = new GridBagConstraints();
			gbcFloorTitle.fill = GridBagConstraints.BOTH;
			gbcFloorTitle.insets = new Insets(0, 0, 5, 0);
			gbcFloorTitle.gridx = 0;
			gbcFloorTitle.gridy = i - 1;
			floorTitlePanel.add(floorTitles[0][i - 1], gbcFloorTitle);
			floorTitlePanel.add(floorTitles[1][i - 1], gbcFloorTitle);
			floorTitlePanel.add(floorTitles[2][i - 1], gbcFloorTitle);
		}
	
		// Create elevator shafts
		JPanel[] displays = new JPanel[elevatorNum];
		floors = new JLabel[elevatorNum][floorNum];
		for(int i = 1; i <= elevatorNum; i++) {
			displays[i - 1] = new JPanel();
			displays[i - 1].setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), new String("Elevator " + Integer.toString(i)), TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbcElevatorDisplay = new GridBagConstraints();
			gbcElevatorDisplay.fill = GridBagConstraints.BOTH;
			gbcElevatorDisplay.insets = new Insets(0, 0, 0, 5);
			gbcElevatorDisplay.gridx = i;
			gbcElevatorDisplay.gridy = 0;
			displayPanel.add(displays[i - 1], gbcElevatorDisplay);
			GridBagLayout gblElevatorDisplay = new GridBagLayout();
			gblElevatorDisplay.columnWidths = new int[] {70};
			gblElevatorDisplay.rowHeights = tempArr;
			gblElevatorDisplay.columnWeights = new double[]{0.0};
			gblElevatorDisplay.rowWeights = temp;
			displays[i - 1].setLayout(gblElevatorDisplay);
	
			// Create the floors for the elevator
			for (int j = 0; j < floorNum; j++) {
				floors[i-1][floorNum - 1 - j] = new JLabel("");
				floors[i-1][floorNum - 1 - j].setIcon(new ImageIcon("./src/main/resources/assets/blank.png"));
				floors[i-1][floorNum - 1 - j].setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbcFloor = new GridBagConstraints();
				gbcFloor.fill = GridBagConstraints.BOTH;
				gbcFloor.insets = new Insets(0, 0, 5, 0);
				gbcFloor.gridx = 0;
				gbcFloor.gridy = j;
				displays[i - 1].add(floors[i-1][floorNum - 1 - j], gbcFloor);
			}
			floors[i-1][0].setIcon(new ImageIcon("./src/main/resources/assets/idle.png"));
		}

		JPanel panel = new JPanel();
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.insets = new Insets(0, 0, 0, 5);
		gbcPanel.fill = GridBagConstraints.BOTH;
		gbcPanel.gridx = columns - 1;
		gbcPanel.gridy = 0;
		displayPanel.add(panel, gbcPanel);

		// Create grid layout if even or odd
		int x;
		if (elevatorNum % 2 == 0) {
			x = elevatorNum / 2;
		}else {
			x = (elevatorNum  / 2) + 1;
		}
		panel.setLayout(new GridLayout(2, x, 0, 0));

		// Create elevator information panels
		JPanel[] elevInfoPanels = new JPanel[elevatorNum];
		elevInfos = new JLabel[elevatorNum][6];
		for(int i = 0; i < elevatorNum ; i++) {
			elevInfoPanels[i] = new JPanel();
			elevInfoPanels[i].setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),new String("Elevator "+ Integer.toString(i+1) +" Info"), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.add(elevInfoPanels[i]);
			elevInfoPanels[i].setLayout(new GridLayout(0, 1, 0, 0));

			elevInfos[i][0] = new JLabel("Current Floor: 1");
			elevInfos[i][0].setFont(new Font("Tahoma", Font.PLAIN, 17));
			elevInfoPanels[i].add(elevInfos[i][0]);

			elevInfos[i][1] = new JLabel("Direction: IDLE");
			elevInfos[i][1].setFont(new Font("Tahoma", Font.PLAIN, 17));
			elevInfoPanels[i].add(elevInfos[i][1]);

			elevInfos[i][2] = new JLabel("Queue Size: 0");
			elevInfos[i][2].setFont(new Font("Tahoma", Font.PLAIN, 17));
			elevInfoPanels[i].add(elevInfos[i][2]);

			elevInfos[i][3] = new JLabel("Motor: IDLE");
			elevInfos[i][3].setFont(new Font("Tahoma", Font.PLAIN, 17));
			elevInfoPanels[i].add(elevInfos[i][3]);
			
			elevInfos[i][4] = new JLabel("Doors: OPEN");
			elevInfos[i][4].setFont(new Font("Tahoma", Font.PLAIN, 17));
			elevInfoPanels[i].add(elevInfos[i][4]);
			
			elevInfos[i][5] = new JLabel("State: IDLE");
			elevInfos[i][5].setFont(new Font("Tahoma", Font.PLAIN, 17));
			elevInfoPanels[i].add(elevInfos[i][5]);
		}
	}
		
	/**
	 * Handle the elevator request and updates the GUI.
	 * @param data ElevatorGuiData, elevator GUI data
	 */
	public void handleElevatorEvent(ElevatorGuiData data) {
		int currentElevatorNum = data.getId()-1;
		int currentFloorNum = data.getCurrentFloor();
		String direction = data.getDirection().toString();
		int queueSize = data.getQueueSize();
		String motor = data.getMotor().toString();
		String door = data.getDoor().toString();
		ElevatorStateEnum currentState = data.getCurrentState();
		if (currentElevatorNum <= elevatorNum && currentFloorNum <= floorNum) {
			for(int floor = 0; floor < floorNum; floor++) {
				floors[currentElevatorNum][floor].setIcon(new ImageIcon("./src/main/resources/assets/blank.png"));
			}
			switch (currentState) {
			case IDLE: {
				floors[currentElevatorNum][currentFloorNum - 1].setIcon(new ImageIcon("./src/main/resources/assets/idle.png"));
				break;
			}
			case DOORS_OPEN: {
				floors[currentElevatorNum][currentFloorNum - 1].setIcon(new ImageIcon("./src/main/resources/assets/open.png"));
				break;
			}
			case DOORS_CLOSED: {
				floors[currentElevatorNum][currentFloorNum - 1].setIcon(new ImageIcon("./src/main/resources/assets/close.png"));
				break;
			}
			case MOVING_DOWN: {
				floors[currentElevatorNum][currentFloorNum - 1].setIcon(new ImageIcon("./src/main/resources/assets/down.png"));
				break;
			}
			case MOVING_UP: {
				floors[currentElevatorNum][currentFloorNum - 1].setIcon(new ImageIcon("./src/main/resources/assets/up.png"));
				break;
			}
			case STOPPED: {
				floors[currentElevatorNum][currentFloorNum - 1].setIcon(new ImageIcon("./src/main/resources/assets/stop.png"));
				break;
			}
			case DOORS_STUCK: {
				floors[currentElevatorNum][currentFloorNum - 1].setIcon(new ImageIcon("./src/main/resources/assets/stuck.png"));
				break;
			}
			case ELEVATOR_STUCK: {
				// Shutdown entire elevator due to hard fault
				for (int i = 0; i < floorNum; i++) {
					floors[currentElevatorNum][i].setIcon(new ImageIcon("./src/main/resources/assets/shutdown.png"));
				}
				break;
			}
			default:
				break;
			}
			elevInfos[currentElevatorNum][0].setText("Current Floor: " + currentFloorNum);
			elevInfos[currentElevatorNum][1].setText("Direction: " + direction);
			elevInfos[currentElevatorNum][2].setText("Queue Size: " + queueSize);
			elevInfos[currentElevatorNum][3].setText("Motor: " + motor);
			elevInfos[currentElevatorNum][4].setText("Door: " + door);
			elevInfos[currentElevatorNum][5].setText("State: " + currentState);
		}
	}
	/**
	 * Handle the floor request and updates the GUI.
	 * @param data FloorGuiData, floor GUI data
	 */
	public void handleFloorEvent(FloorGuiData data) {
		if (data.getUpButtonLamp()) {
			floorTitles[0][data.getFloorNum()].setIcon(new ImageIcon("./src/main/resources/assets/active-floor-up.png"));
		} else {
			floorTitles[0][data.getFloorNum()].setIcon(new ImageIcon("./src/main/resources/assets/inactive-floor-up.png"));
		}
		if (data.getDownButtonLamp()) {
			floorTitles[2][data.getFloorNum()].setIcon(new ImageIcon("./src/main/resources/assets/active-floor-down.png"));
		} else {
			floorTitles[2][data.getFloorNum()].setIcon(new ImageIcon("./src/main/resources/assets/inactive-floor-down.png"));
		}
	}
	
	/**
	 * Listener for floor data.
	 */
	private void listenForFloorData() {
		DatagramPacket packet;
		FloorGuiData data;
		
		while (true) {
			data = null;
			packet = floorDtoSocket.receiveMessage();
			try {
				data = FloorGuiData.decode(UDPClient.readPacketData(packet));
			} catch (ClassNotFoundException | IOException e) {
				floorDtoSocket.close();
				e.printStackTrace();
				System.exit(1);
			}
			handleFloorEvent(data);
		}
	}
	
	/**
	 * Listener for elevator data.
	 */
	private void listenForElevatorData() {
		DatagramPacket packet;
		ElevatorGuiData data;
		
		while (true) {
			data = null;
			packet = elevatorDtoSocket.receiveMessage();
			try {
				data = ElevatorGuiData.decode(UDPClient.readPacketData(packet));
			} catch (ClassNotFoundException | IOException e) {
				elevatorDtoSocket.close();
				e.printStackTrace();
				System.exit(1);
			}
			handleElevatorEvent(data);
		}
	}

	/**
	 * Thread run method.
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		displayGUI();	
		// initialize socket listeners
		new Thread(this::listenForFloorData).start();
		new Thread(this::listenForElevatorData).start();
	}
	
	/**
	 * Main method to execute own thread.
	 * @param args, default parameter
	 */
	public static void main(String[] args) {
		GUI gui = new GUI(new SimulatorConfiguration("./src/main/resources/config.properties"));
		new Thread(gui).start();
	}
}
