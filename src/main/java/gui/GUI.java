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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
 * @author Trong Nguyen
 * @version 1.0, 04/03/23
 */
public class GUI extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private static int elevatorNum;
	private static int floorNum;
	private JLabel[][] floors;
	private JLabel[][] elevInfos;
	private UDPClient floorDtoSocket;
	private UDPClient elevatorDtoSocket;
	
	public GUI(SimulatorConfiguration config) {
		elevatorNum = config.NUM_ELEVATORS;
		floorNum = config.NUM_FLOORS;
		// initialize sockets
		floorDtoSocket = new UDPClient(config.GUI_FLOOR_DTO_PORT);
		elevatorDtoSocket = new UDPClient(config.GUI_ELEVATOR_DTO_PORT);
	}
	
	private void displayGUI() {
		int frameHeight = 165 + 30 * floorNum;
		int frameWidth = 50 + (50 * elevatorNum);
		
		if (elevatorNum % 2 == 0) {//add width for elevator data
			frameWidth += (elevatorNum / 2) * 350;
		}else {
			frameWidth += ((elevatorNum / 2) + 1) * 350;
		}

		frameWidth += 160;
		
		this.setTitle("ELEVATOR-CONTROL-SYSTEM-AND-SIMULATOR");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("./src/main/resource/assets/favicon.png"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, frameWidth, frameHeight + 165);
		this.setPreferredSize(new Dimension(1080, 740));
		this.setResizable(false);
		this.setVisible(true);
	
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
		
		GridBagLayout gblDisplayPanel = new GridBagLayout();
		int columns = 1 + elevatorNum + 1; // adds the floor column, elevator columns, then the data column
		int[] columnWidth = new int[columns];
		for (int i = 0; i < columns; i++) {
			if(i != 1 + elevatorNum) {
				columnWidth[i] = 70;
			}else {
				if (elevatorNum % 2 == 0) {//add width for elevator data
					columnWidth[i] = (elevatorNum / 2) * 350;
				}else {
					columnWidth[i] = ((elevatorNum / 2) + 1) * 350;
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
		
		
		//initialize floor titles
		JPanel floorTitlePanel = new JPanel();
		floorTitlePanel.setBackground(UIManager.getColor("Button.background"));
		floorTitlePanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Floors", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		GridBagConstraints gbc_floorTitlePanel = new GridBagConstraints();
		gbc_floorTitlePanel.fill = GridBagConstraints.VERTICAL;
		gbc_floorTitlePanel.insets = new Insets(0, 0, 0, 5);
		gbc_floorTitlePanel.gridx = 0;
		gbc_floorTitlePanel.gridy = 0;
		displayPanel.add(floorTitlePanel, gbc_floorTitlePanel);
		
		GridBagLayout gbl_floorTitlePanel = new GridBagLayout();
		gbl_floorTitlePanel.columnWidths = new int[] {50};
		int[] tempArr = new int[floorNum];
		//initialize the temp array
		for (int j = 0; j < floorNum; j++) {
			tempArr[j] = 30;
		}
		gbl_floorTitlePanel.rowHeights = tempArr;
		gbl_floorTitlePanel.columnWeights = new double[]{0.0};
		double[] temp = new double[floorNum];
		for (int j = 0; j < floorNum; j++) {
			temp[j] = 1.0;
		}
		gbl_floorTitlePanel.rowWeights = temp;
		floorTitlePanel.setLayout(gbl_floorTitlePanel);
		
		JLabel[] floorTitles = new JLabel[floorNum];
		for(int i = 1; i <= floorNum; i++) {
			floorTitles[i - 1] = new JLabel(Integer.toString(floorNum - i + 1));
			floorTitles[i - 1].setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints floorTitle = new GridBagConstraints();
			floorTitle.fill = GridBagConstraints.BOTH;
			floorTitle.insets = new Insets(0, 0, 5, 0);
			floorTitle.gridx = 0;
			floorTitle.gridy = i - 1;
			floorTitlePanel.add(floorTitles[i - 1], floorTitle);
		}
	
		JPanel[] displays = new JPanel[elevatorNum];
		floors = new JLabel[elevatorNum][floorNum];
		//create the elevator displays
		for(int i = 1; i <= elevatorNum; i++) {
			displays[i - 1] = new JPanel();
			displays[i - 1].setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), new String("Elevator " + Integer.toString(i - 1)), TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_elevatorDisplay = new GridBagConstraints();
			gbc_elevatorDisplay.fill = GridBagConstraints.BOTH;
			gbc_elevatorDisplay.insets = new Insets(0, 0, 0, 5);
			gbc_elevatorDisplay.gridx = i;
			gbc_elevatorDisplay.gridy = 0;
			displayPanel.add(displays[i - 1], gbc_elevatorDisplay);
			GridBagLayout gbl_elevatorDisplay = new GridBagLayout();
			gbl_elevatorDisplay.columnWidths = new int[] {70};
			gbl_elevatorDisplay.rowHeights = tempArr;
			gbl_elevatorDisplay.columnWeights = new double[]{0.0};
			gbl_elevatorDisplay.rowWeights = temp;
			displays[i - 1].setLayout(gbl_elevatorDisplay);
	
			//create the floors for the elevator
			for (int j = 0; j < floorNum; j++) {
				floors[i-1][floorNum - 1 - j] = new JLabel("");
				floors[i-1][floorNum - 1 - j].setIcon(new ImageIcon("./src/main/resources/assets/closed.png"));
				floors[i-1][floorNum - 1 - j].setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_floor = new GridBagConstraints();
				gbc_floor.fill = GridBagConstraints.BOTH;
				gbc_floor.insets = new Insets(0, 0, 5, 0);
				gbc_floor.gridx = 0;
				gbc_floor.gridy = j;
				displays[i - 1].add(floors[i-1][floorNum - 1 - j], gbc_floor);
			}
			
			floors[i-1][0].setIcon(new ImageIcon("./src/main/resources/assets/moving.jpg"));
		}
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = columns - 1;
		gbc_panel.gridy = 0;
		displayPanel.add(panel, gbc_panel);

		int x;
		if (elevatorNum % 2 == 0) {//create grid layout if even or odd
			x = elevatorNum / 2;
		}else {
			x = (elevatorNum  / 2) + 1;
		}

		panel.setLayout(new GridLayout(2, x, 0, 0));

		JPanel[] elevInfoPanels = new JPanel[elevatorNum];
		elevInfos = new JLabel[elevatorNum][4];
		for(int i = 0; i < elevatorNum ; i++) {
			elevInfoPanels[i] = new JPanel();
			elevInfoPanels[i].setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),new String("Elevator "+ Integer.toString(i) +" Info"), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.add(elevInfoPanels[i]);
			elevInfoPanels[i].setLayout(new GridLayout(0, 1, 0, 0));

			elevInfos[i][0] = new JLabel("Current Floor: 1");
			elevInfos[i][0].setFont(new Font("Tahoma", Font.PLAIN, 17));
			elevInfoPanels[i].add(elevInfos[i][0]);

			elevInfos[i][1] = new JLabel("Direction: IDLE");
			elevInfos[i][1].setFont(new Font("Tahoma", Font.PLAIN, 17));
			elevInfoPanels[i].add(elevInfos[i][1]);

			elevInfos[i][2] = new JLabel("Requests: STANDING BY");
			elevInfos[i][2].setFont(new Font("Tahoma", Font.PLAIN, 17));
			elevInfoPanels[i].add(elevInfos[i][2]);

			elevInfos[i][3] = new JLabel("Doors: CLOSED");
			elevInfos[i][3].setFont(new Font("Tahoma", Font.PLAIN, 17));
			elevInfoPanels[i].add(elevInfos[i][3]);
		}
	}
	
	public void handleElevatorEvent(int currentElevatorNum, int currentFloorNum, ElevatorStateEnum state) {
		if (currentElevatorNum <= elevatorNum && currentFloorNum <= floorNum) {
			switch (state) {
			case DOORS_OPEN: {
				floors[currentElevatorNum][currentFloorNum - 1].setIcon(new ImageIcon("./src/main/resources/assets/open.png"));
				elevInfos[currentElevatorNum][0].setText("Current Floor: " + currentFloorNum);
				elevInfos[currentElevatorNum][3].setText("Doors: OPEN");
			}
			case DOORS_CLOSED: {
				floors[currentElevatorNum][currentFloorNum - 1].setIcon(new ImageIcon("./src/main/resources/assets/closed.png"));
				elevInfos[currentElevatorNum][0].setText("Current Floor: " + currentFloorNum);
				elevInfos[currentElevatorNum][3].setText("Doors: CLOSED");
			}
			case DOORS_STUCK: {
				floors[currentElevatorNum][currentFloorNum - 1].setIcon(new ImageIcon("./src/main/resources/assets/stuck.png"));
				elevInfos[currentElevatorNum][0].setText("Current Floor: " + currentFloorNum);
				elevInfos[currentElevatorNum][3].setText("Doors: STUCK");
			}
			case MOVING_DOWN: {
				floors[currentElevatorNum][currentFloorNum - 1].setIcon(new ImageIcon("./src/main/resources/assets/moving.jpg"));
				if (currentFloorNum > 1) {
					floors[currentElevatorNum][currentFloorNum - 2].setIcon(new ImageIcon("./src/main/resources/assets/closed.png"));
				}
				if (currentFloorNum < floorNum) {
					floors[currentElevatorNum][currentFloorNum].setIcon(new ImageIcon("./src/main/resources/assets/closed.png"));
				}
				elevInfos[currentElevatorNum][0].setText("Current Floor: " + currentFloorNum);
				elevInfos[currentElevatorNum][1].setText("Direction: MOVING_DOWN");
			}
			case MOVING_UP: {
				floors[currentElevatorNum][currentFloorNum - 1].setIcon(new ImageIcon("./src/main/resources/assets/moving.jpg"));
				if (currentFloorNum > 1) {
					floors[currentElevatorNum][currentFloorNum - 2].setIcon(new ImageIcon("./src/main/resources/assets/closed.png"));
				}
				if (currentFloorNum < floorNum) {
					floors[currentElevatorNum][currentFloorNum].setIcon(new ImageIcon("./src/main/resources/assets/closed.png"));
				}
				elevInfos[currentElevatorNum][0].setText("Current Floor: " + currentFloorNum);
				elevInfos[currentElevatorNum][1].setText("Direction: MOVING_UP");
			}
			case ELEVATOR_STUCK: {
				for (int i = 0; i < floorNum; i++) {
					floors[currentElevatorNum][i].setIcon(new ImageIcon("./src/main/resources/assets/shutdown.png"));
				}
				elevInfos[currentElevatorNum][3].setText("Doors: SHUTDOWN");
			}
			default:
				break;
			}
		}
	}
	
	public void handleRequestsInfo(int currentElevatorNum, ArrayList<Integer> arr) {
		String temp = "Requests: ";
		if (arr.isEmpty()) {
			temp += "IDLE";
		}
			
		else {
			temp += arr.toString();
		}
		elevInfos[currentElevatorNum][2].setText(temp);
	}
	
	public int getNumElevators() {
		return elevatorNum;
	}
	
	public int getNumFloors() {
		return floorNum;
	}

	public void print(JTextArea consoleLog, String message) {
		consoleLog.append(" " + message + "\n");
	}
	
	private void listenForFloorData() {
		DatagramPacket packet;
		FloorGuiData data;
		
		System.out.println("Method not implemented yet.");
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
			// TODO: read the data and then do something with it
			System.out.println(data.toString());
		}
	}
	
	private void listenForElevatorData() {
		DatagramPacket packet;
		ElevatorGuiData data;
		
		System.out.println("Method not implemented yet.");
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
			// TODO: do something with the data
			System.out.println(data.toString());
		}
	}

	@Override
	public void run() {
		displayGUI();	
		// initialize socket listeners
		new Thread(this::listenForFloorData).start();
		new Thread(this::listenForElevatorData).start();
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI(new SimulatorConfiguration("./src/main/resources/config.properties"));
		new Thread(gui).start();
	}
	
}
