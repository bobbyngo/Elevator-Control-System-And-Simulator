<mxfile host="app.diagrams.net" modified="2023-04-10T20:57:59.188Z" agent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36" etag="lN0xrhAPqkAjLwARwN9W" version="21.1.4" type="google" pages="4">
  <diagram id="fP-uMRKBXJGwMI5BY12X" name="UML Class Diagram">
    <mxGraphModel grid="1" page="0" gridSize="10" guides="1" tooltips="1" connect="1" arrows="1" fold="1" pageScale="1" pageWidth="850" pageHeight="1100" math="0" shadow="0">
      <root>
        <mxCell id="0" />
        <mxCell id="1" parent="0" />
        <mxCell id="6k2OaxNk0oD0N80I3I0B-21" value="IdleState" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;" vertex="1" parent="1">
          <mxGeometry x="-350" y="994" width="250" height="86" as="geometry" />
        </mxCell>
        <mxCell id="6k2OaxNk0oD0N80I3I0B-23" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="6k2OaxNk0oD0N80I3I0B-21">
          <mxGeometry y="26" width="250" height="8" as="geometry" />
        </mxCell>
        <mxCell id="mVBOx95oI3bT0EHTXn39-15" value="+ IdleState(ElevatorContext)" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="6k2OaxNk0oD0N80I3I0B-21">
          <mxGeometry y="34" width="250" height="26" as="geometry" />
        </mxCell>
        <mxCell id="6k2OaxNk0oD0N80I3I0B-24" value="+ handleRequestReceived(): ElevatorState" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="6k2OaxNk0oD0N80I3I0B-21">
          <mxGeometry y="60" width="250" height="26" as="geometry" />
        </mxCell>
        <mxCell id="_OIYQrUqf0P2R9oleHmv-4" value="ElevatorState" style="swimlane;fontStyle=3;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;" vertex="1" parent="1">
          <mxGeometry x="100" y="620" width="250" height="216" as="geometry" />
        </mxCell>
        <mxCell id="_OIYQrUqf0P2R9oleHmv-5" value="- context: ElevatorContext" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="_OIYQrUqf0P2R9oleHmv-4">
          <mxGeometry y="26" width="250" height="26" as="geometry" />
        </mxCell>
        <mxCell id="_OIYQrUqf0P2R9oleHmv-6" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="_OIYQrUqf0P2R9oleHmv-4">
          <mxGeometry y="52" width="250" height="8" as="geometry" />
        </mxCell>
        <mxCell id="jEQuOQHjatojKekzEs3H-2" value="+ ElevatorState(ElevatorContext)" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="_OIYQrUqf0P2R9oleHmv-4">
          <mxGeometry y="60" width="250" height="26" as="geometry" />
        </mxCell>
        <mxCell id="_OIYQrUqf0P2R9oleHmv-7" value="+ handleRequestReceived(): ElevatorState" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fontStyle=2" vertex="1" parent="_OIYQrUqf0P2R9oleHmv-4">
          <mxGeometry y="86" width="250" height="26" as="geometry" />
        </mxCell>
        <mxCell id="OKjeKfP07qgNGmVYYudD-2" value="+ handleDoorsOpen(): ElevatorState" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fontStyle=2" vertex="1" parent="_OIYQrUqf0P2R9oleHmv-4">
          <mxGeometry y="112" width="250" height="26" as="geometry" />
        </mxCell>
        <mxCell id="aXwwfYzjIC2WkBZoKQ7B-1" value="+ handleDoorsClose(): ElevatorState" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fontStyle=2" vertex="1" parent="_OIYQrUqf0P2R9oleHmv-4">
          <mxGeometry y="138" width="250" height="26" as="geometry" />
        </mxCell>
        <mxCell id="aXwwfYzjIC2WkBZoKQ7B-2" value="+ handleMotorThrottle(): ElevatorState" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fontStyle=2" vertex="1" parent="_OIYQrUqf0P2R9oleHmv-4">
          <mxGeometry y="164" width="250" height="26" as="geometry" />
        </mxCell>
        <mxCell id="aXwwfYzjIC2WkBZoKQ7B-3" value="+ handleMotorStop(): ElevatorState" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fontStyle=2" vertex="1" parent="_OIYQrUqf0P2R9oleHmv-4">
          <mxGeometry y="190" width="250" height="26" as="geometry" />
        </mxCell>
        <mxCell id="xLhY6Sd1dolUkYcHQHLL-1" value="DoorsOpenState" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;" vertex="1" parent="1">
          <mxGeometry x="-70" y="994" width="210" height="86" as="geometry" />
        </mxCell>
        <mxCell id="xLhY6Sd1dolUkYcHQHLL-3" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="xLhY6Sd1dolUkYcHQHLL-1">
          <mxGeometry y="26" width="210" height="8" as="geometry" />
        </mxCell>
        <mxCell id="mVBOx95oI3bT0EHTXn39-16" value="+ DoorsOpenState(ElevatorContext)" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="xLhY6Sd1dolUkYcHQHLL-1">
          <mxGeometry y="34" width="210" height="26" as="geometry" />
        </mxCell>
        <mxCell id="xLhY6Sd1dolUkYcHQHLL-4" value="+ handleDoorsClose(): ElevatorState" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="xLhY6Sd1dolUkYcHQHLL-1">
          <mxGeometry y="60" width="210" height="26" as="geometry" />
        </mxCell>
        <mxCell id="xLhY6Sd1dolUkYcHQHLL-5" value="DoorsClosedState" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;" vertex="1" parent="1">
          <mxGeometry x="170" y="994" width="230" height="86" as="geometry" />
        </mxCell>
        <mxCell id="xLhY6Sd1dolUkYcHQHLL-7" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="xLhY6Sd1dolUkYcHQHLL-5">
          <mxGeometry y="26" width="230" height="8" as="geometry" />
        </mxCell>
        <mxCell id="xLhY6Sd1dolUkYcHQHLL-8" value="+ DoorsClosedState(ElevatorContext)" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="xLhY6Sd1dolUkYcHQHLL-5">
          <mxGeometry y="34" width="230" height="26" as="geometry" />
        </mxCell>
        <mxCell id="mVBOx95oI3bT0EHTXn39-17" value="+ handleThrottleMotor(): ElevatorState" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="xLhY6Sd1dolUkYcHQHLL-5">
          <mxGeometry y="60" width="230" height="26" as="geometry" />
        </mxCell>
        <mxCell id="PvW4bDL-lUkdsxErx_tO-1" value="StoppedState" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;" vertex="1" parent="1">
          <mxGeometry x="430" y="994" width="215" height="86" as="geometry" />
        </mxCell>
        <mxCell id="PvW4bDL-lUkdsxErx_tO-3" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="PvW4bDL-lUkdsxErx_tO-1">
          <mxGeometry y="26" width="215" height="8" as="geometry" />
        </mxCell>
        <mxCell id="mVBOx95oI3bT0EHTXn39-18" value="+ StoppedState(ElevatorContext)" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="PvW4bDL-lUkdsxErx_tO-1">
          <mxGeometry y="34" width="215" height="26" as="geometry" />
        </mxCell>
        <mxCell id="PvW4bDL-lUkdsxErx_tO-4" value="+ handleDoorsOpen(): ElevatorState" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="PvW4bDL-lUkdsxErx_tO-1">
          <mxGeometry y="60" width="215" height="26" as="geometry" />
        </mxCell>
        <mxCell id="O7bUzLqHbdXUlx723xl2-1" value="MovingUpState" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;" vertex="1" parent="1">
          <mxGeometry x="670" y="994" width="210" height="86" as="geometry" />
        </mxCell>
        <mxCell id="O7bUzLqHbdXUlx723xl2-3" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="O7bUzLqHbdXUlx723xl2-1">
          <mxGeometry y="26" width="210" height="8" as="geometry" />
        </mxCell>
        <mxCell id="mVBOx95oI3bT0EHTXn39-19" value="+ MovingUpState(ElevatorContext)" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="O7bUzLqHbdXUlx723xl2-1">
          <mxGeometry y="34" width="210" height="26" as="geometry" />
        </mxCell>
        <mxCell id="O7bUzLqHbdXUlx723xl2-4" value="+ handleMotorStop(): ElevatorState" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="O7bUzLqHbdXUlx723xl2-1">
          <mxGeometry y="60" width="210" height="26" as="geometry" />
        </mxCell>
        <mxCell id="O7bUzLqHbdXUlx723xl2-5" value="MovingDownState" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;" vertex="1" parent="1">
          <mxGeometry x="910" y="994" width="220" height="86" as="geometry" />
        </mxCell>
        <mxCell id="O7bUzLqHbdXUlx723xl2-7" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="O7bUzLqHbdXUlx723xl2-5">
          <mxGeometry y="26" width="220" height="8" as="geometry" />
        </mxCell>
        <mxCell id="mVBOx95oI3bT0EHTXn39-20" value="+ MovingDownState(ElevatorContext)" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="O7bUzLqHbdXUlx723xl2-5">
          <mxGeometry y="34" width="220" height="26" as="geometry" />
        </mxCell>
        <mxCell id="O7bUzLqHbdXUlx723xl2-8" value="+ handleMotorStop(): ElevatorState" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="O7bUzLqHbdXUlx723xl2-5">
          <mxGeometry y="60" width="220" height="26" as="geometry" />
        </mxCell>
        <mxCell id="OKjeKfP07qgNGmVYYudD-3" value="" style="endArrow=block;endSize=16;endFill=0;html=1;rounded=0;edgeStyle=elbowEdgeStyle;elbow=vertical;" edge="1" parent="1" source="6k2OaxNk0oD0N80I3I0B-21" target="_OIYQrUqf0P2R9oleHmv-4">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="40" y="864" as="sourcePoint" />
            <mxPoint x="200" y="864" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="OKjeKfP07qgNGmVYYudD-9" value="" style="endArrow=block;endSize=16;endFill=0;html=1;rounded=0;edgeStyle=elbowEdgeStyle;elbow=vertical;" edge="1" parent="1" source="xLhY6Sd1dolUkYcHQHLL-1" target="_OIYQrUqf0P2R9oleHmv-4">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-130" y="994" as="sourcePoint" />
            <mxPoint x="195" y="822" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="OKjeKfP07qgNGmVYYudD-10" value="" style="endArrow=block;endSize=16;endFill=0;html=1;rounded=0;edgeStyle=elbowEdgeStyle;elbow=vertical;" edge="1" parent="1" source="xLhY6Sd1dolUkYcHQHLL-5" target="_OIYQrUqf0P2R9oleHmv-4">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="70" y="994" as="sourcePoint" />
            <mxPoint x="195" y="822" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="OKjeKfP07qgNGmVYYudD-11" value="" style="endArrow=block;endSize=16;endFill=0;html=1;rounded=0;edgeStyle=elbowEdgeStyle;elbow=vertical;" edge="1" parent="1" source="PvW4bDL-lUkdsxErx_tO-1" target="_OIYQrUqf0P2R9oleHmv-4">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="250" y="994" as="sourcePoint" />
            <mxPoint x="195" y="822" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="OKjeKfP07qgNGmVYYudD-12" value="" style="endArrow=block;endSize=16;endFill=0;html=1;rounded=0;edgeStyle=elbowEdgeStyle;elbow=vertical;" edge="1" parent="1" source="O7bUzLqHbdXUlx723xl2-1" target="_OIYQrUqf0P2R9oleHmv-4">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="435" y="994" as="sourcePoint" />
            <mxPoint x="195" y="822" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="OKjeKfP07qgNGmVYYudD-13" value="" style="endArrow=block;endSize=16;endFill=0;html=1;rounded=0;edgeStyle=elbowEdgeStyle;elbow=vertical;" edge="1" parent="1" source="O7bUzLqHbdXUlx723xl2-5" target="_OIYQrUqf0P2R9oleHmv-4">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="630" y="994" as="sourcePoint" />
            <mxPoint x="195" y="822" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="6t3dsR7ptzXQsS--LP_T-9" value="&amp;lt;&amp;lt;enumeration&amp;gt;&amp;gt;&lt;br&gt;&lt;b&gt;Door&lt;/b&gt;" style="swimlane;fontStyle=0;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=40;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=0;marginBottom=0;html=1;" vertex="1" parent="1">
          <mxGeometry x="-390" y="1470" width="140" height="100" as="geometry" />
        </mxCell>
        <mxCell id="6t3dsR7ptzXQsS--LP_T-10" value="OPEN" style="text;html=1;strokeColor=none;fillColor=none;align=left;verticalAlign=middle;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="6t3dsR7ptzXQsS--LP_T-9">
          <mxGeometry y="40" width="140" height="30" as="geometry" />
        </mxCell>
        <mxCell id="u2ZBep82UUWYPAD2wlvD-1" value="CLOSED" style="text;html=1;strokeColor=none;fillColor=none;align=left;verticalAlign=middle;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="6t3dsR7ptzXQsS--LP_T-9">
          <mxGeometry y="70" width="140" height="30" as="geometry" />
        </mxCell>
        <mxCell id="TOGdVY98yAAXkXNdqq6X-1" value="&amp;lt;&amp;lt;enumeration&amp;gt;&amp;gt;&lt;br&gt;&lt;b&gt;Motor&lt;/b&gt;" style="swimlane;fontStyle=0;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=40;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=0;marginBottom=0;html=1;" vertex="1" parent="1">
          <mxGeometry x="-560" y="1470" width="140" height="130" as="geometry" />
        </mxCell>
        <mxCell id="TOGdVY98yAAXkXNdqq6X-2" value="THROTTLE_UP" style="text;html=1;strokeColor=none;fillColor=none;align=left;verticalAlign=middle;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="TOGdVY98yAAXkXNdqq6X-1">
          <mxGeometry y="40" width="140" height="30" as="geometry" />
        </mxCell>
        <mxCell id="TOGdVY98yAAXkXNdqq6X-3" value="THROTTLE_DOWN" style="text;html=1;strokeColor=none;fillColor=none;align=left;verticalAlign=middle;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="TOGdVY98yAAXkXNdqq6X-1">
          <mxGeometry y="70" width="140" height="30" as="geometry" />
        </mxCell>
        <mxCell id="TOGdVY98yAAXkXNdqq6X-4" value="IDLE" style="text;html=1;strokeColor=none;fillColor=none;align=left;verticalAlign=middle;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="TOGdVY98yAAXkXNdqq6X-1">
          <mxGeometry y="100" width="140" height="30" as="geometry" />
        </mxCell>
        <mxCell id="eG5xdeh4SAmd4YAF4qDd-1" value="&amp;lt;&amp;lt;enumeration&amp;gt;&amp;gt;&lt;br&gt;&lt;b&gt;Direction&lt;/b&gt;" style="swimlane;fontStyle=0;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=40;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=0;marginBottom=0;html=1;" vertex="1" parent="1">
          <mxGeometry x="-740" y="1470" width="140" height="130" as="geometry" />
        </mxCell>
        <mxCell id="eG5xdeh4SAmd4YAF4qDd-2" value="UP" style="text;html=1;strokeColor=none;fillColor=none;align=left;verticalAlign=middle;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="eG5xdeh4SAmd4YAF4qDd-1">
          <mxGeometry y="40" width="140" height="30" as="geometry" />
        </mxCell>
        <mxCell id="eG5xdeh4SAmd4YAF4qDd-3" value="DOWN" style="text;html=1;strokeColor=none;fillColor=none;align=left;verticalAlign=middle;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="eG5xdeh4SAmd4YAF4qDd-1">
          <mxGeometry y="70" width="140" height="30" as="geometry" />
        </mxCell>
        <mxCell id="eG5xdeh4SAmd4YAF4qDd-4" value="IDLE" style="text;html=1;strokeColor=none;fillColor=none;align=left;verticalAlign=middle;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="eG5xdeh4SAmd4YAF4qDd-1">
          <mxGeometry y="100" width="140" height="30" as="geometry" />
        </mxCell>
        <mxCell id="S04EVWkAnAZHa5UmRraD-2" value="" style="endArrow=diamondThin;endFill=0;endSize=24;html=1;rounded=0;elbow=vertical;" edge="1" parent="1">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-654.9648049399011" y="560" as="sourcePoint" />
            <mxPoint x="-656.7820945945946" y="500" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="S04EVWkAnAZHa5UmRraD-8" value="SimulatorConfiguration" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;" vertex="1" parent="1">
          <mxGeometry x="-270" y="180" width="225" height="242" as="geometry" />
        </mxCell>
        <mxCell id="S04EVWkAnAZHa5UmRraD-9" value="- numFloors: int" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="S04EVWkAnAZHa5UmRraD-8">
          <mxGeometry y="26" width="225" height="26" as="geometry" />
        </mxCell>
        <mxCell id="S04EVWkAnAZHa5UmRraD-12" value="- numElevators: int" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="S04EVWkAnAZHa5UmRraD-8">
          <mxGeometry y="52" width="225" height="26" as="geometry" />
        </mxCell>
        <mxCell id="S04EVWkAnAZHa5UmRraD-13" value="- openDoorsTime: int" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="S04EVWkAnAZHa5UmRraD-8">
          <mxGeometry y="78" width="225" height="26" as="geometry" />
        </mxCell>
        <mxCell id="S04EVWkAnAZHa5UmRraD-14" value="- closeDoorsTime: int" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="S04EVWkAnAZHa5UmRraD-8">
          <mxGeometry y="104" width="225" height="26" as="geometry" />
        </mxCell>
        <mxCell id="S04EVWkAnAZHa5UmRraD-17" value="- closeDoorsTime: int" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="S04EVWkAnAZHa5UmRraD-8">
          <mxGeometry y="130" width="225" height="26" as="geometry" />
        </mxCell>
        <mxCell id="S04EVWkAnAZHa5UmRraD-18" value="- loadingTime: int" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="S04EVWkAnAZHa5UmRraD-8">
          <mxGeometry y="156" width="225" height="26" as="geometry" />
        </mxCell>
        <mxCell id="S04EVWkAnAZHa5UmRraD-19" value="- movingTime: int" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="S04EVWkAnAZHa5UmRraD-8">
          <mxGeometry y="182" width="225" height="26" as="geometry" />
        </mxCell>
        <mxCell id="S04EVWkAnAZHa5UmRraD-10" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="S04EVWkAnAZHa5UmRraD-8">
          <mxGeometry y="208" width="225" height="8" as="geometry" />
        </mxCell>
        <mxCell id="S04EVWkAnAZHa5UmRraD-11" value="+ SimulatorConfiguration(String)" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="S04EVWkAnAZHa5UmRraD-8">
          <mxGeometry y="216" width="225" height="26" as="geometry" />
        </mxCell>
        <mxCell id="S04EVWkAnAZHa5UmRraD-16" value="" style="endArrow=diamondThin;endFill=1;endSize=24;html=1;rounded=0;elbow=vertical;edgeStyle=elbowEdgeStyle;" edge="1" parent="1" target="_OIYQrUqf0P2R9oleHmv-4">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-500" y="727.8823529411766" as="sourcePoint" />
            <mxPoint x="-110" y="770" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="S04EVWkAnAZHa5UmRraD-20" value="" style="endArrow=diamondThin;endFill=1;endSize=24;html=1;rounded=0;edgeStyle=elbowEdgeStyle;elbow=vertical;" edge="1" parent="1" source="6t3dsR7ptzXQsS--LP_T-9">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-820" y="1200" as="sourcePoint" />
            <mxPoint x="-645" y="1218" as="targetPoint" />
            <Array as="points">
              <mxPoint x="-260" y="1400" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="S04EVWkAnAZHa5UmRraD-21" value="" style="endArrow=diamondThin;endFill=1;endSize=24;html=1;rounded=0;edgeStyle=elbowEdgeStyle;elbow=vertical;" edge="1" parent="1" source="TOGdVY98yAAXkXNdqq6X-1">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-410" y="1210" as="sourcePoint" />
            <mxPoint x="-645" y="1218" as="targetPoint" />
            <Array as="points">
              <mxPoint x="-400" y="1400" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="S04EVWkAnAZHa5UmRraD-22" value="" style="endArrow=diamondThin;endFill=1;endSize=24;html=1;rounded=0;edgeStyle=elbowEdgeStyle;elbow=vertical;entryX=0.422;entryY=1.003;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="eG5xdeh4SAmd4YAF4qDd-1" target="7ej0JeDtoSVcsfH2DCIB-3">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-590" y="1210" as="sourcePoint" />
            <mxPoint x="-645" y="1218" as="targetPoint" />
            <Array as="points">
              <mxPoint x="-570" y="1400" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="nDF-ZZZx2z4eVH97TIbB-8" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;SchedulerSubsystem&lt;/b&gt;&lt;br&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;- context: SchedulerContext&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;- simulatorConfiguration: SimulatorConfiguration&lt;/p&gt;- pendingRequestSocket: UDPClient&lt;br&gt;- arrivalRequestSocket: UDPClient&lt;br&gt;- completedRequestSocket: UDPClient&lt;br&gt;&lt;br&gt;- pendingRequestListenerThread: Thread&lt;br&gt;- arrivalRequestListenerThread: Thread&lt;br&gt;- completedRequestListenerThread: Thread&lt;br&gt;- logConsole: LogConsole&lt;br&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ SchedulerSubsystem(SimulatorConfiguration)&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ run(): void&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ receivePendingRequest():&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ sendPendingRequest(ElevatorRequest): void&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ receiveArrivalNotification():&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ sendArrivalNotification(ElevatorRequest): void&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ receiveCompletedElevatorRequest:&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ sendCompletedElevatorRequest(ElevatorRequest): void&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;&amp;nbsp;+ getPendingRequestListenerThread(): Thread&lt;br&gt;&amp;nbsp;+&amp;nbsp;getArrivalRequestListenerThread(): Thread&lt;br&gt;&amp;nbsp;+&amp;nbsp;getCompletedRequestListenerThread(): Thread&lt;br&gt;&amp;nbsp;+&amp;nbsp;getSimulatorConfiguration(): SimulatorConfiuration&lt;br&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;u&gt;+ main(String[]): void&lt;/u&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;" style="verticalAlign=top;align=left;overflow=fill;fontSize=12;fontFamily=Helvetica;html=1;whiteSpace=wrap;" vertex="1" parent="1">
          <mxGeometry x="-2195" y="178.5" width="380" height="500" as="geometry" />
        </mxCell>
        <mxCell id="nDF-ZZZx2z4eVH97TIbB-9" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;SchedulerContext&lt;/b&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;- availableElevators: ArrayList&amp;lt;ElevatorContext&amp;gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;- schedulerSubsystem: SchedulerSubsystem&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;- pendingElevatorRequests: ArrayList&amp;lt;ElevatorRequest&amp;gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;- completedElevatorRequests: ArrayList&amp;lt;ElevatorRequest&amp;gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;- currentState: SchedulerState&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ SchedulerContext(ElevatorSubsystem)&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;- findTheClosestElevatorToRequestFloor(ArrayList, int): ElevatorStatus&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;- findTheAvailableIdleElevator(ElevatorRequest): ElevatorStatus&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;- findTheAvailableMovingElevator(Direction, int): ElevatorStatus&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ findBestElevatorToAssignRequest(ArrayList&amp;lt;ElevatorRequest&amp;gt;): AssignedElevatorRequest&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ forwardRequestToElevator(Elevator, ElevatorRequest): void&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ assignNextBestElevatorRequest(): void&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ addAvailableElevatorStatus(ElevatorRequest): void&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ modifyAvailableElevatorStatus(ElevatorRequest): voi&lt;span style=&quot;background-color: initial;&quot;&gt;d&lt;/span&gt;&lt;span style=&quot;background-color: initial;&quot;&gt;&amp;nbsp;&lt;/span&gt;&lt;/p&gt;&amp;nbsp;+ addPendingElevatorRequests(ElevatorRequest): void&lt;br&gt;&amp;nbsp;+ addCompletedElevatorRequests(ElevatorRequest): void&lt;br&gt;&amp;nbsp;+&amp;nbsp;onRequestReceived(): void&lt;br&gt;&amp;nbsp;+&amp;nbsp;onRequestSent(): void&lt;br&gt;&amp;nbsp;+ isSchedulerIdle(): boolean&lt;br&gt;&amp;nbsp;+&amp;nbsp;processCompletedElevatorRequest(): void&lt;br&gt;&amp;nbsp;+&amp;nbsp;getAvailableElevatorStatus(): List&lt;br&gt;&amp;nbsp;+&amp;nbsp;getPendingElevatorRequests(): List&lt;br&gt;&amp;nbsp;+&amp;nbsp;getCompletedElevatorRequests(): List&lt;br&gt;&amp;nbsp;+&amp;nbsp;getCurrentState(): SchedulerState" style="verticalAlign=top;align=left;overflow=fill;fontSize=12;fontFamily=Helvetica;html=1;whiteSpace=wrap;" vertex="1" parent="1">
          <mxGeometry x="-2260" y="751.5" width="510" height="526" as="geometry" />
        </mxCell>
        <mxCell id="nDF-ZZZx2z4eVH97TIbB-11" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;&lt;i&gt;SchedulerState&lt;/i&gt;&lt;/b&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;- context: SchedulerContext&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ SchedulerState(SchedulerContext)&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+&lt;i&gt; handleRequest()&lt;/i&gt;: &lt;i&gt;SchedulerState&lt;/i&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;i&gt;+ handleResponse(): SchedulerState&lt;/i&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;" style="verticalAlign=top;align=left;overflow=fill;fontSize=12;fontFamily=Helvetica;html=1;whiteSpace=wrap;" vertex="1" parent="1">
          <mxGeometry x="-2830" y="900" width="350" height="109" as="geometry" />
        </mxCell>
        <mxCell id="nDF-ZZZx2z4eVH97TIbB-13" value="1" style="endArrow=open;html=1;endSize=12;startArrow=diamondThin;startSize=14;startFill=0;edgeStyle=orthogonalEdgeStyle;align=left;verticalAlign=bottom;rounded=0;exitX=-0.003;exitY=0.38;exitDx=0;exitDy=0;exitPerimeter=0;entryX=1;entryY=0.5;entryDx=0;entryDy=0;" edge="1" parent="1" source="nDF-ZZZx2z4eVH97TIbB-9" target="nDF-ZZZx2z4eVH97TIbB-11">
          <mxGeometry x="-0.7847" y="22" relative="1" as="geometry">
            <mxPoint x="-2230" y="980" as="sourcePoint" />
            <mxPoint x="-2070" y="980" as="targetPoint" />
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="nDF-ZZZx2z4eVH97TIbB-14" value="" style="endArrow=diamondThin;endFill=0;endSize=24;html=1;rounded=0;entryX=0.5;entryY=1;entryDx=0;entryDy=0;exitX=0.5;exitY=0;exitDx=0;exitDy=0;" edge="1" parent="1" source="nDF-ZZZx2z4eVH97TIbB-9" target="nDF-ZZZx2z4eVH97TIbB-8">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-2170" y="688.5" as="sourcePoint" />
            <mxPoint x="-2010" y="688.5" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="nDF-ZZZx2z4eVH97TIbB-16" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;InServiceState&lt;/b&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ method(): Type&lt;/p&gt;" style="verticalAlign=top;align=left;overflow=fill;fontSize=12;fontFamily=Helvetica;html=1;whiteSpace=wrap;" vertex="1" parent="1">
          <mxGeometry x="-2560" y="1180" width="160" height="120" as="geometry" />
        </mxCell>
        <mxCell id="nDF-ZZZx2z4eVH97TIbB-19" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;IdleState&lt;/b&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ method(): Type&lt;/p&gt;" style="verticalAlign=top;align=left;overflow=fill;fontSize=12;fontFamily=Helvetica;html=1;whiteSpace=wrap;" vertex="1" parent="1">
          <mxGeometry x="-2890" y="1180" width="160" height="120" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-1" value="Extends" style="endArrow=block;endSize=16;endFill=0;html=1;rounded=0;entryX=0.5;entryY=1;entryDx=0;entryDy=0;exitX=0.5;exitY=0;exitDx=0;exitDy=0;" edge="1" parent="1" source="nDF-ZZZx2z4eVH97TIbB-19" target="nDF-ZZZx2z4eVH97TIbB-11">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-2830" y="1310" as="sourcePoint" />
            <mxPoint x="-2670" y="1310" as="targetPoint" />
            <Array as="points">
              <mxPoint x="-2810" y="1110" />
              <mxPoint x="-2655" y="1110" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-2" value="Extends" style="endArrow=block;endSize=16;endFill=0;html=1;rounded=0;exitX=0.5;exitY=0;exitDx=0;exitDy=0;" edge="1" parent="1" source="nDF-ZZZx2z4eVH97TIbB-16">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-2650" y="1180" as="sourcePoint" />
            <mxPoint x="-2570" y="1010" as="targetPoint" />
            <Array as="points">
              <mxPoint x="-2480" y="1110" />
              <mxPoint x="-2570" y="1110" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-3" value="SerializableEncoder" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="-1570" y="610" width="280" height="80" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-5" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-3">
          <mxGeometry y="26" width="280" height="8" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-6" value="+ encode(object: Serializable): byte[]&lt;br&gt;+ decode(data: byte[]): Object" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;whiteSpace=wrap;html=1;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-3">
          <mxGeometry y="34" width="280" height="46" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-7" value="UDPClient" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="-300" y="440" width="360" height="260" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-8" value="&lt;div&gt;- BUF_SIZE: int&lt;/div&gt;&lt;div&gt;- socket: DatagramSocket&lt;/div&gt;" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;whiteSpace=wrap;html=1;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-7">
          <mxGeometry y="26" width="360" height="44" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-9" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-7">
          <mxGeometry y="70" width="360" height="8" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-10" value="&lt;div&gt;+ UDPClient()&lt;/div&gt;&lt;div&gt;+ UDPClient(port: int)&lt;/div&gt;&lt;div&gt;+ close(): void&lt;/div&gt;&lt;div&gt;+ sendMessage(data: byte[] , destAddr: InetAddress , destPort: int): DatagramPacket&lt;/div&gt;&lt;div&gt;+ sendMessage(data: byte[] , destAddr: String , destPort: int ): DatagramPacket&lt;/div&gt;&lt;div&gt;+ receiveMessage(): DatagramPacket&lt;/div&gt;&lt;div&gt;+ readPacketData(packet: DatagramPacket): byte[]&lt;/div&gt;&lt;div&gt;+ formatPacketData(packet: DatagramPacket): String&lt;/div&gt;&lt;div&gt;+ static void main(String[] args)&lt;/div&gt;" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;whiteSpace=wrap;html=1;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-7">
          <mxGeometry y="78" width="360" height="182" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-11" value="AssignedElevatorRequest" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="-1610" y="1510" width="410" height="230" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-12" value="&lt;div&gt;- serialVersionUID: long&lt;/div&gt;&lt;div&gt;- elevatorID&lt;/div&gt;" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;whiteSpace=wrap;html=1;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-11">
          <mxGeometry y="26" width="410" height="44" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-13" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-11">
          <mxGeometry y="70" width="410" height="8" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-14" value="&lt;div&gt;+ AssignedElevatorRequest(assignedElevatorId: int, request: ElevatorRequest)&lt;/div&gt;&lt;div&gt;+ AssignedElevatorRequest(int assignedElevatorId, String timestampString,&amp;nbsp;&lt;/div&gt;&lt;div&gt;Integer sourceFloor, Direction direction, Integer destinationFloor)&lt;/div&gt;&lt;div&gt;+ getElevatorId(): int&lt;/div&gt;&lt;div&gt;+ decode(byte[] data): AssignedElevatorRequest&lt;/div&gt;&lt;div&gt;+ encode(): byte[]&lt;/div&gt;&lt;div&gt;+ static void main(String[] args)&lt;/div&gt;" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;whiteSpace=wrap;html=1;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-11">
          <mxGeometry y="78" width="410" height="152" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-15" value="ElevatorRequest" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="-1210" y="780" width="260" height="380" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-16" value="&lt;div&gt;- serialVersionUID: long&amp;nbsp;&lt;/div&gt;&lt;div&gt;- timestamp: Timestamp&amp;nbsp;&lt;/div&gt;&lt;div&gt;- sourceFloor: Integer&amp;nbsp;&lt;/div&gt;&lt;div&gt;- direction: Direction&amp;nbsp;&lt;/div&gt;&lt;div&gt;- destinationFloor: Integer&amp;nbsp;&lt;/div&gt;" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;whiteSpace=wrap;html=1;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-15">
          <mxGeometry y="26" width="260" height="94" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-17" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-15">
          <mxGeometry y="120" width="260" height="8" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-18" value="&lt;div&gt;+ ElevatorRequest(Timestamp timestamp, Integer sourceFloor,&amp;nbsp;&lt;/div&gt;&lt;div&gt;Direction direction, Integer destinationFloor)&lt;/div&gt;&lt;div&gt;+ ElevatorRequest(String timestampString, Integer sourceFloor,&amp;nbsp;&lt;/div&gt;&lt;div&gt;Direction direction, Integer destinationFloor)&lt;/div&gt;&lt;div&gt;+ Timestamp getTimestamp()&lt;/div&gt;&lt;div&gt;+ Integer getSourceFloor()&lt;/div&gt;&lt;div&gt;+ Direction getDirection()&lt;/div&gt;&lt;div&gt;+ Integer getDestinationFloor()&lt;/div&gt;&lt;div&gt;+ String toString()&lt;/div&gt;&lt;div&gt;+ boolean equals(Object obj)&lt;/div&gt;&lt;div&gt;+ Timestamp stringToTimestamp(String timestampString)&lt;/div&gt;&lt;div&gt;+ ElevatorRequest decode(byte[] data)&lt;/div&gt;&lt;div&gt;+ byte[] encode()&lt;/div&gt;" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;whiteSpace=wrap;html=1;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-15">
          <mxGeometry y="128" width="260" height="252" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-19" value="ElevatorStatus" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="-1480" y="780" width="190" height="380" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-20" value="&lt;div&gt;- static final long serialVersionUID&lt;/div&gt;&lt;div&gt;- int elevatorId&lt;/div&gt;&lt;div&gt;- int floor&lt;/div&gt;&lt;div&gt;- Direction direction&lt;/div&gt;&lt;div&gt;- ElevatorStateEnum state&lt;/div&gt;&lt;div&gt;- int numRequests&lt;/div&gt;" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;whiteSpace=wrap;html=1;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-19">
          <mxGeometry y="26" width="190" height="114" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-21" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-19">
          <mxGeometry y="140" width="190" height="8" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-22" value="&lt;div&gt;+ ElevatorStatus(ElevatorContext ctx)&lt;/div&gt;&lt;div&gt;+ ElevatorStatus(int id)&lt;/div&gt;&lt;div&gt;+ ElevatorStatus(int id, int floor, Direction direction,&amp;nbsp;&lt;/div&gt;&lt;div&gt;int numRequests, ElevatorStateEnum state)&lt;/div&gt;&lt;div&gt;+ int getElevatorId()&lt;/div&gt;&lt;div&gt;+ int getFloor()&lt;/div&gt;&lt;div&gt;+ Direction getDirection()&lt;/div&gt;&lt;div&gt;+ int getNumRequests()&lt;/div&gt;&lt;div&gt;+ ElevatorStatus decode(byte[] data)&lt;/div&gt;&lt;div&gt;+ byte[] encode()&lt;/div&gt;&lt;div&gt;+ String toString()&lt;/div&gt;" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;whiteSpace=wrap;html=1;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-19">
          <mxGeometry y="148" width="190" height="232" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-23" value="RequestListenerTask" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="-410" y="-1" width="230" height="150" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-24" value="- elevatorSubsystem: ElevatorSubsystem" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;whiteSpace=wrap;html=1;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-23">
          <mxGeometry y="26" width="230" height="44" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-25" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-23">
          <mxGeometry y="70" width="230" height="8" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-26" value="&lt;div&gt;+ RequestListenerTask(ElevatorSubsystem es)&lt;/div&gt;&lt;div&gt;+ run(): void&lt;/div&gt;" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;whiteSpace=wrap;html=1;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-23">
          <mxGeometry y="78" width="230" height="72" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-27" value="FloorComponents" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="-1100" y="-398" width="300" height="278" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-28" value="&lt;div&gt;- HashMap&amp;lt;Direction, Boolean&amp;gt; upButtonHashMap&lt;/div&gt;&lt;div&gt;- HashMap&amp;lt;Direction, Boolean&amp;gt; downButtonHashMap&lt;/div&gt;&lt;div&gt;- Direction directionLamp&lt;/div&gt;&lt;div&gt;- boolean arrivalSensor&lt;/div&gt;" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;whiteSpace=wrap;html=1;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-27">
          <mxGeometry y="26" width="300" height="74" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-29" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-27">
          <mxGeometry y="100" width="300" height="8" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-30" value="&lt;div&gt;+ FloorComponents(Direction direction)&lt;/div&gt;&lt;div&gt;+ boolean getButtonLampStatus(Direction direction)&lt;/div&gt;&lt;div&gt;+ Direction getDirectionLamp()&lt;/div&gt;&lt;div&gt;+ boolean getArrivalSensor()&lt;/div&gt;&lt;div&gt;+ updateButtonDirectionStatus(Direction direction): void&lt;/div&gt;&lt;div&gt;+ updateDirectionLamp(Direction directionLamp): void&lt;/div&gt;&lt;div&gt;+ updateArrivalSensor(boolean arrivalSensor): void&lt;/div&gt;&lt;div&gt;+ toString():String&lt;/div&gt;" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;whiteSpace=wrap;html=1;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-27">
          <mxGeometry y="108" width="300" height="170" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-31" value="Parse" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="-1545" y="-410" width="360" height="220" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-32" value="&lt;div&gt;- Logger logger&lt;/div&gt;&lt;div&gt;- FileReader input&lt;/div&gt;&lt;div&gt;- BufferedReader reader&lt;/div&gt;&lt;div&gt;- String lineEntry&lt;/div&gt;&lt;div&gt;- ArrayList&amp;lt;ElevatorRequest&amp;gt; elevatorRequestList&lt;/div&gt;" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;whiteSpace=wrap;html=1;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-31">
          <mxGeometry y="26" width="360" height="94" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-33" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;strokeColor=inherit;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-31">
          <mxGeometry y="120" width="360" height="8" as="geometry" />
        </mxCell>
        <mxCell id="lApMr0xc0mvT29AV6tHW-34" value="&lt;div&gt;+ Parser(String fileName)&lt;/div&gt;&lt;div&gt;+ String fillTimestampZero(String textRequest)&lt;/div&gt;&lt;div&gt;+ ElevatorRequest textParser(String textRequest)&lt;/div&gt;&lt;div&gt;+ sortListByTimestamp(ArrayList&amp;lt;ElevatorRequest&amp;gt; requestList)&lt;/div&gt;&lt;div&gt;+ ArrayList&amp;lt;ElevatorRequest&amp;gt; requestParser()&lt;/div&gt;" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;whiteSpace=wrap;html=1;" vertex="1" parent="lApMr0xc0mvT29AV6tHW-31">
          <mxGeometry y="128" width="360" height="92" as="geometry" />
        </mxCell>
        <mxCell id="Sp53R-B_jACgne9Q10kD-1" value="" style="endArrow=classic;startArrow=classic;html=1;rounded=0;exitX=1.009;exitY=0.704;exitDx=0;exitDy=0;exitPerimeter=0;entryX=0;entryY=0.854;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="nDF-ZZZx2z4eVH97TIbB-8" target="7ej0JeDtoSVcsfH2DCIB-15">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="-1710" y="463" as="sourcePoint" />
            <mxPoint x="-1550" y="460" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-5" value="" style="endArrow=diamondThin;endFill=1;endSize=24;html=1;rounded=0;exitX=0.25;exitY=0;exitDx=0;exitDy=0;" edge="1" parent="1" source="lApMr0xc0mvT29AV6tHW-15">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1550" y="790" as="sourcePoint" />
            <mxPoint x="-1210" y="590" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-6" value="" style="endArrow=diamondThin;endFill=1;endSize=24;html=1;rounded=0;entryX=0;entryY=0.5;entryDx=0;entryDy=0;exitX=0.918;exitY=-0.01;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="lApMr0xc0mvT29AV6tHW-15">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1010" y="770" as="sourcePoint" />
            <mxPoint x="-827.5" y="389" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-7" value="" style="endArrow=diamondThin;endFill=1;endSize=24;html=1;rounded=0;entryX=0;entryY=0.5;entryDx=0;entryDy=0;exitX=1.01;exitY=0.282;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="lApMr0xc0mvT29AV6tHW-18">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1550" y="790" as="sourcePoint" />
            <mxPoint x="-790" y="853" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-8" value="" style="endArrow=diamondThin;endFill=1;endSize=24;html=1;rounded=0;exitX=0.354;exitY=1.012;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="lApMr0xc0mvT29AV6tHW-18" target="lApMr0xc0mvT29AV6tHW-11">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1550" y="930" as="sourcePoint" />
            <mxPoint x="-1390" y="930" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-9" value="Use" style="endArrow=open;endSize=12;dashed=1;html=1;rounded=0;exitX=0.467;exitY=0.006;exitDx=0;exitDy=0;exitPerimeter=0;entryX=0.456;entryY=1.015;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="7ej0JeDtoSVcsfH2DCIB-14" target="lApMr0xc0mvT29AV6tHW-34">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1355" y="-110" as="sourcePoint" />
            <mxPoint x="-1355" y="-190" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-10" value="" style="endArrow=diamondThin;endFill=1;endSize=24;html=1;rounded=0;exitX=0.795;exitY=-0.01;exitDx=0;exitDy=0;exitPerimeter=0;entryX=1;entryY=0.75;entryDx=0;entryDy=0;" edge="1" parent="1" source="lApMr0xc0mvT29AV6tHW-15" target="7ej0JeDtoSVcsfH2DCIB-15">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1420" y="530" as="sourcePoint" />
            <mxPoint x="-1100" y="210" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-11" value="" style="endArrow=diamondThin;endFill=0;endSize=24;html=1;rounded=0;entryX=0;entryY=0.5;entryDx=0;entryDy=0;exitX=0.75;exitY=0;exitDx=0;exitDy=0;" edge="1" parent="1" source="lApMr0xc0mvT29AV6tHW-19">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1420" y="800" as="sourcePoint" />
            <mxPoint x="-827.5" y="311" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-12" value="" style="endArrow=diamondThin;endFill=0;endSize=24;html=1;rounded=0;entryX=0;entryY=0.5;entryDx=0;entryDy=0;exitX=0.874;exitY=1.001;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="lApMr0xc0mvT29AV6tHW-22">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1420" y="800" as="sourcePoint" />
            <mxPoint x="-790" y="1205.0000000000005" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-13" value="" style="endArrow=diamondThin;endFill=0;endSize=24;html=1;rounded=0;entryX=1.013;entryY=0.78;entryDx=0;entryDy=0;entryPerimeter=0;exitX=0.011;exitY=0.325;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="lApMr0xc0mvT29AV6tHW-20" target="nDF-ZZZx2z4eVH97TIbB-8">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1420" y="800" as="sourcePoint" />
            <mxPoint x="-1260" y="800" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-14" value="" style="endArrow=diamondThin;endFill=0;endSize=24;html=1;rounded=0;entryX=1.005;entryY=0.835;entryDx=0;entryDy=0;entryPerimeter=0;exitX=-0.018;exitY=0.18;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="lApMr0xc0mvT29AV6tHW-22" target="nDF-ZZZx2z4eVH97TIbB-9">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1420" y="930" as="sourcePoint" />
            <mxPoint x="-1260" y="930" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-15" value="Use" style="endArrow=open;endSize=12;dashed=1;html=1;rounded=0;entryX=-0.022;entryY=0.077;entryDx=0;entryDy=0;entryPerimeter=0;exitX=1.01;exitY=0.212;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" target="S04EVWkAnAZHa5UmRraD-13">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-484.0999999999999" y="243.51199999999972" as="sourcePoint" />
            <mxPoint x="-770" y="620" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-17" value="Use" style="endArrow=open;endSize=12;dashed=1;html=1;rounded=0;entryX=-0.018;entryY=0.231;entryDx=0;entryDy=0;entryPerimeter=0;exitX=1.009;exitY=0.981;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" target="S04EVWkAnAZHa5UmRraD-18">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-497.3899999999999" y="637.5060000000003" as="sourcePoint" />
            <mxPoint x="-770" y="620" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-19" value="Use" style="endArrow=open;endSize=12;dashed=1;html=1;rounded=0;exitX=0;exitY=0.5;exitDx=0;exitDy=0;" edge="1" parent="1" source="lApMr0xc0mvT29AV6tHW-14">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-930" y="1070" as="sourcePoint" />
            <mxPoint x="-1880" y="1280" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-20" value="Use" style="endArrow=open;endSize=12;dashed=1;html=1;rounded=0;entryX=0;entryY=0.5;entryDx=0;entryDy=0;exitX=1;exitY=0.081;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="lApMr0xc0mvT29AV6tHW-11">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-930" y="1070" as="sourcePoint" />
            <mxPoint x="-790" y="1075.0000000000005" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-21" value="Use" style="endArrow=open;endSize=12;dashed=1;html=1;rounded=0;exitX=0.408;exitY=-0.003;exitDx=0;exitDy=0;exitPerimeter=0;entryX=0.491;entryY=1.076;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="lApMr0xc0mvT29AV6tHW-19" target="lApMr0xc0mvT29AV6tHW-6">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1250" y="710" as="sourcePoint" />
            <mxPoint x="-1090" y="710" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-22" value="Use" style="endArrow=open;endSize=12;dashed=1;html=1;rounded=0;exitX=0.027;exitY=-0.01;exitDx=0;exitDy=0;exitPerimeter=0;entryX=0.821;entryY=1;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="lApMr0xc0mvT29AV6tHW-15" target="lApMr0xc0mvT29AV6tHW-6">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1250" y="710" as="sourcePoint" />
            <mxPoint x="-1090" y="710" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-23" value="Use" style="endArrow=open;endSize=12;dashed=1;html=1;rounded=0;entryX=-0.003;entryY=0.124;entryDx=0;entryDy=0;entryPerimeter=0;exitX=1;exitY=0.5;exitDx=0;exitDy=0;" edge="1" parent="1" source="7ej0JeDtoSVcsfH2DCIB-14" target="lApMr0xc0mvT29AV6tHW-30">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1090" y="460" as="sourcePoint" />
            <mxPoint x="-1460" y="480" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-25" value="Use" style="endArrow=open;endSize=12;dashed=1;html=1;rounded=0;entryX=0.478;entryY=1.014;entryDx=0;entryDy=0;entryPerimeter=0;exitX=1.015;exitY=0.068;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" target="lApMr0xc0mvT29AV6tHW-26">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-482.39999999999964" y="183.12000000000012" as="sourcePoint" />
            <mxPoint x="-550" y="60" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-26" value="Use" style="endArrow=open;endSize=12;dashed=1;html=1;rounded=0;entryX=0.001;entryY=0.126;entryDx=0;entryDy=0;entryPerimeter=0;exitX=1.003;exitY=1.154;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" target="lApMr0xc0mvT29AV6tHW-10">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-486.48" y="328.0039999999999" as="sourcePoint" />
            <mxPoint x="-500" y="690" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="G4TfzvWvCtWxYGwZXU_d-27" value="Use" style="endArrow=open;endSize=12;dashed=1;html=1;rounded=0;entryX=0;entryY=0.56;entryDx=0;entryDy=0;entryPerimeter=0;exitX=1;exitY=0.5;exitDx=0;exitDy=0;" edge="1" parent="1" target="lApMr0xc0mvT29AV6tHW-10">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-500" y="833" as="sourcePoint" />
            <mxPoint x="-500" y="690" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="7ej0JeDtoSVcsfH2DCIB-2" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;ElevatorSubsystem&lt;/b&gt;&lt;br&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;/p&gt;- elevators: HashMap&amp;lt;Integer, ElevatorContext&amp;gt;&lt;br&gt;- udpClient: UDPClient&lt;br&gt;- simulatorConfiguration SimulatorConfiguration&lt;br&gt;- requestListenerThread: Thread&lt;br&gt;&lt;hr size=&quot;1&quot;&gt;&amp;nbsp;+ ElevatorSubsystem(SimulatorConfiguration)&lt;br&gt;&amp;nbsp;+ run: void&lt;br&gt;&amp;nbsp;+ getConfig: SimulatorConfiguration&amp;nbsp;&lt;br&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ receiveElevatorRequest(): void&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;- routeElevatorRequest(AssignedElevatorRequest): void&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ sendCompletedElevatorRequest(ElevatorRequest): void&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ notifyContextUpdate(ElevatorContext) : void&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ sendArrivalNotification(ElevatorContext): void&lt;/p&gt;&lt;div&gt;&amp;nbsp;+ sendGuiNotification(ElevatorGuiData): void&lt;/div&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ returnElevatorRequests(List&amp;lt;ElevatorRequest&amp;gt;): void&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;u&gt;+ main(String[]):void&lt;/u&gt;&lt;/p&gt;" style="verticalAlign=top;align=left;overflow=fill;fontSize=12;fontFamily=Helvetica;html=1;whiteSpace=wrap;" vertex="1" parent="1">
          <mxGeometry x="-830" y="170" width="340" height="330" as="geometry" />
        </mxCell>
        <mxCell id="7ej0JeDtoSVcsfH2DCIB-3" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;ElevatorContext&lt;/b&gt;&lt;br&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;/p&gt;- elevatorID; int&lt;br&gt;- externalRequests: ArrayList&amp;lt;ElevatorRequest&amp;gt;&lt;br&gt;- internalRequests: ArrayList&amp;lt;ElevatorRequest&amp;gt;&lt;br&gt;- currentState: ElevatorState&lt;br&gt;- currentFloor: int&lt;br&gt;- motor: Motor&lt;br&gt;- direction: Direction&lt;br&gt;- door: Door&lt;br&gt;- elevatorButtonBoard: HashMap&amp;lt;Integer, Boolean&amp;gt;&lt;br style=&quot;border-color: var(--border-color); padding: 0px; margin: 0px;&quot;&gt;&lt;span style=&quot;&quot;&gt;&amp;nbsp;- logConsole: LogConsole&lt;br&gt;&lt;/span&gt;- timer: Timer&lt;br&gt;- simulatorConfig: SimulatorConfiguration&lt;br&gt;&lt;hr size=&quot;1&quot;&gt;&amp;nbsp;+ ElevatorContext(SimulatorConfiguration, int)&lt;br&gt;&amp;nbsp;+ startElevator(): void&lt;br&gt;&amp;nbsp;+ addExternalRequest(ElevatorRequest request)&lt;br&gt;&amp;nbsp;+ onRequestReceived(): void&lt;br&gt;&amp;nbsp;+&amp;nbsp;onTimeout(TimeoutEvent event): void&lt;br&gt;&amp;nbsp;+&amp;nbsp;loadPassengers(): void&lt;br&gt;&amp;nbsp;+&amp;nbsp;unloadPassengers(): void&lt;br&gt;&amp;nbsp;+&amp;nbsp;pressElevatorButton(int floor): void&lt;br&gt;&amp;nbsp;+&amp;nbsp;clearElevatorButton(int floor): void&lt;br&gt;&amp;nbsp;+ setTimer(TimerTask, int): void&lt;br&gt;&amp;nbsp;+ killTimer(): void&lt;br&gt;&amp;nbsp;+&amp;nbsp;setDirection(Direction d): void&lt;br&gt;&amp;nbsp;+&amp;nbsp;setDoors(Door d): void&lt;br&gt;&amp;nbsp;+ setMotor(Motor m): void&lt;br&gt;&amp;nbsp;+&amp;nbsp;incrementCurrentFloor(): boolean&lt;br&gt;&amp;nbsp;+&amp;nbsp;decrementCurrentFloor(): boolean&lt;br&gt;&amp;nbsp;+&amp;nbsp;getConfig():&amp;nbsp;SimulatorConfiguration&lt;br&gt;&amp;nbsp;+&amp;nbsp;toString(): String&lt;br&gt;&amp;nbsp;+&amp;nbsp;getId(): int&lt;br&gt;&amp;nbsp;+&amp;nbsp;getDirection(): Direction&lt;br&gt;+&amp;nbsp;getNumRequests(): int&lt;br&gt;+&amp;nbsp;getExternalRequests(): List&amp;lt;ElevatorRequest&amp;gt;&lt;br&gt;+&amp;nbsp;&amp;nbsp;getInternalRequests(): List&amp;lt;ElevatorRequest&amp;gt;&lt;br&gt;+&amp;nbsp;getElevatorLampStatus(Integer floor): boolean&lt;br&gt;+ getAllSelectedFloors():ArrayList&amp;lt;Integer&amp;gt;&lt;br&gt;+&amp;nbsp;&amp;nbsp;getCurrentFloor(): int&lt;br&gt;+&amp;nbsp;calculateNextDirection(): Direction&lt;br&gt;+&amp;nbsp;shouldContinueSweepingUp(): boolean&lt;br&gt;+&amp;nbsp;shouldContinueSweepingDown(): boolean&lt;br&gt;+ determineNextDirection(): Direction&lt;br&gt;+&amp;nbsp;shouldElevatorStop(): boolean&lt;br&gt;+&amp;nbsp;existsExternalRequestsAbove(): boolean&lt;br&gt;+&amp;nbsp;existsExternalRequestsBelow()&lt;br&gt;+&amp;nbsp;isAtErrorFloor(): ElevatorError&lt;br&gt;+&amp;nbsp;notifyArrivalSensor()&lt;br&gt;+&amp;nbsp;returnExternalRequests()&lt;br&gt;&lt;u&gt;+ main(String[]):void&lt;/u&gt;&lt;br&gt;&lt;br&gt;&lt;br&gt;&lt;br&gt;&lt;br&gt;&lt;br&gt;&lt;br&gt;&lt;br&gt;&lt;br&gt;&lt;br&gt;&lt;br&gt;&lt;br&gt;&lt;br&gt;&lt;br&gt;&lt;br&gt;&lt;br&gt;&lt;br&gt;" style="verticalAlign=top;align=left;overflow=fill;fontSize=12;fontFamily=Helvetica;html=1;whiteSpace=wrap;" vertex="1" parent="1">
          <mxGeometry x="-790" y="560" width="345" height="760" as="geometry" />
        </mxCell>
        <mxCell id="7ej0JeDtoSVcsfH2DCIB-12" value="" style="endArrow=none;html=1;rounded=0;exitX=0.5;exitY=0;exitDx=0;exitDy=0;" edge="1" parent="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="-870" y="1470" as="sourcePoint" />
            <mxPoint x="-670" y="1400" as="targetPoint" />
            <Array as="points">
              <mxPoint x="-870" y="1400" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="7ej0JeDtoSVcsfH2DCIB-13" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;i&gt;&amp;lt;&amp;lt;enumerator&amp;gt;&amp;gt;&lt;/i&gt;&lt;br&gt;&lt;b&gt;ElevatorError&lt;/b&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;DOORS_STUCK&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;ELEVATOR_STUCK&lt;/p&gt;" style="verticalAlign=top;align=left;overflow=fill;fontSize=12;fontFamily=Helvetica;html=1;whiteSpace=wrap;" vertex="1" parent="1">
          <mxGeometry x="-970" y="1470" width="190" height="140" as="geometry" />
        </mxCell>
        <mxCell id="7ej0JeDtoSVcsfH2DCIB-14" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;Floor&lt;/b&gt;&lt;br&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;/p&gt;- floorNum: int&lt;br&gt;- components: FloorComponents&lt;br style=&quot;border-color: var(--border-color); padding: 0px; margin: 0px;&quot;&gt;&lt;hr size=&quot;1&quot;&gt;&amp;nbsp;+ Floor(int floorNum): void&lt;br&gt;&amp;nbsp;+ setFloorUpLamp(boolean status): void&lt;br&gt;&amp;nbsp;+ setFloorDownLamp(boolean status): void&lt;br&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ getFloorUpLamp(): boolean&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+&amp;nbsp;getFloorDownLamp(): boolean&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ setFloorSensor(int sensorId, boolean status): void&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ setElevatorDirectionLamp(int elevatorId, Direction direction): void&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ getUpButtonLamp(): void&lt;/p&gt;&amp;nbsp;+ getDownButtonLamp(): void&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ getFloorNum(): int&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ toString(): String&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;" style="verticalAlign=top;align=left;overflow=fill;fontSize=12;fontFamily=Helvetica;html=1;whiteSpace=wrap;" vertex="1" parent="1">
          <mxGeometry x="-1570" y="-130" width="410" height="240" as="geometry" />
        </mxCell>
        <mxCell id="7ej0JeDtoSVcsfH2DCIB-15" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;FloorSubsystem&lt;/b&gt;&lt;br&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;/p&gt;- logger: Logger&lt;br&gt;- file: File&lt;br&gt;- simulatorConfiguration: SimulatorConfiguration&lt;br&gt;- parser: Parser&lt;br&gt;- udpArrivalRequestsReceiver: UDPClient&lt;br&gt;- udpCompletedRequestsReceiver: UDPClient&amp;nbsp;&lt;br&gt;-&amp;nbsp;floorArr: Array&lt;br&gt;- numOfFloors: int&lt;br&gt;- logConsole: LogConsole&lt;br style=&quot;border-color: var(--border-color); padding: 0px; margin: 0px;&quot;&gt;&lt;hr size=&quot;1&quot;&gt;&amp;nbsp;+ FloorSubsystem(SimulatorConfiguration): void&lt;br&gt;&amp;nbsp;+ run(): void&lt;br&gt;&amp;nbsp;+ addRequestsToQueue(ArrayList&amp;lt;ElevatorRequest&amp;gt;): void&lt;br&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ listenToArrivalRequests(): void&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+&amp;nbsp;updateAllElevatorLamps(int, Direction): void&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ updateAllSensorsStatus(int): void&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+&amp;nbsp;listenToCompletedRequests(): void&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ getElevatorRequests(): ArrayList&lt;/p&gt;&amp;nbsp;+ sendGuiNotification(FloorGuiData): void&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ printLog(String): void&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;u&gt;+ main(String[]): void&lt;/u&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;" style="verticalAlign=top;align=left;overflow=fill;fontSize=12;fontFamily=Helvetica;html=1;whiteSpace=wrap;" vertex="1" parent="1">
          <mxGeometry x="-1550" y="170" width="380" height="410" as="geometry" />
        </mxCell>
        <mxCell id="7ej0JeDtoSVcsfH2DCIB-16" value="" style="endArrow=diamondThin;endFill=1;endSize=24;html=1;rounded=0;exitX=0.433;exitY=0.003;exitDx=0;exitDy=0;exitPerimeter=0;entryX=0.458;entryY=1.013;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="7ej0JeDtoSVcsfH2DCIB-15" target="7ej0JeDtoSVcsfH2DCIB-14">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-993" y="786" as="sourcePoint" />
            <mxPoint x="-1090" y="220" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="7ej0JeDtoSVcsfH2DCIB-17" value="(1,n)" style="text;html=1;align=center;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="1">
          <mxGeometry x="-1390" y="140" width="50" height="30" as="geometry" />
        </mxCell>
        <mxCell id="7ej0JeDtoSVcsfH2DCIB-19" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;FloorGuiData&lt;/b&gt;&lt;br&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;/p&gt;- serialVersionUID: long&lt;br&gt;- floorNum: int&lt;br&gt;- upButtonLamp: boolean&lt;br&gt;- downButtonlamp: boolean&lt;br style=&quot;border-color: var(--border-color); padding: 0px; margin: 0px;&quot;&gt;&lt;hr size=&quot;1&quot;&gt;&amp;nbsp;+ FloorGuiData(int , boolean, boolean): void&lt;br&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ getUpButtonLamp(): boolean&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+&amp;nbsp;getDownButtonLamp(): boolean&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ getFloorNum(): int&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ encode(): byte[]&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;br&gt;&lt;/p&gt;" style="verticalAlign=top;align=left;overflow=fill;fontSize=12;fontFamily=Helvetica;html=1;whiteSpace=wrap;" vertex="1" parent="1">
          <mxGeometry x="-1860" y="-120" width="260" height="200" as="geometry" />
        </mxCell>
        <mxCell id="5X_Inju6__3TvkD5niFS-1" value="" style="endArrow=diamondThin;endFill=0;endSize=24;html=1;rounded=0;elbow=vertical;entryX=-0.005;entryY=0.146;entryDx=0;entryDy=0;entryPerimeter=0;exitX=0.553;exitY=0.999;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="7ej0JeDtoSVcsfH2DCIB-19" target="7ej0JeDtoSVcsfH2DCIB-15">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1678.1848049399011" y="178.5" as="sourcePoint" />
            <mxPoint x="-1680.0020945945946" y="118.5" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="5X_Inju6__3TvkD5niFS-2" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;ElevatorGuiData&lt;/b&gt;&lt;br&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;/p&gt;- serialVersionUID: long&lt;br&gt;- id: int&lt;br&gt;-&amp;nbsp; currentState:ElevatorStateEnum&lt;div&gt;&lt;span style=&quot;background-color: initial;&quot;&gt;-&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;background-color: initial;&quot;&gt;currentFloor: int&lt;/span&gt;&lt;/div&gt;&lt;div&gt;- motor: Motor&lt;/div&gt;&lt;div&gt;- direction: Direction&lt;/div&gt;&lt;div&gt;- door: Door&lt;span style=&quot;background-color: initial;&quot;&gt;&amp;nbsp;&lt;/span&gt;&lt;/div&gt;-&amp;nbsp; destinationFloors: TreeSet&amp;lt;Integer&amp;gt;&lt;br&gt;- sourceFloors: TreeSet&amp;lt;Integer&amp;gt;&lt;hr size=&quot;1&quot;&gt;&amp;nbsp;+ ElevatorGuiData(ElevatorContext): void&lt;br&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ decode(bte[]): ElevatorGuiData&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ encode(): byte[]&lt;/p&gt;&lt;p style=&quot;border-color: var(--border-color); margin: 0px 0px 0px 4px;&quot;&gt;+&amp;nbsp;getId(): int&lt;/p&gt;&lt;p style=&quot;border-color: var(--border-color); margin: 0px 0px 0px 4px;&quot;&gt;+ getCurrentState(): ElevatorStateEnum&lt;/p&gt;&lt;p style=&quot;border-color: var(--border-color); margin: 0px 0px 0px 4px;&quot;&gt;&lt;/p&gt;&lt;p style=&quot;border-color: var(--border-color); margin: 0px 0px 0px 4px;&quot;&gt;+ getCurrentFloor():int&lt;/p&gt;&lt;p style=&quot;border-color: var(--border-color); margin: 0px 0px 0px 4px;&quot;&gt;+ getMotor(): Motor&lt;/p&gt;&lt;p style=&quot;border-color: var(--border-color); margin: 0px 0px 0px 4px;&quot;&gt;+ getDirection(): Direction&lt;/p&gt;&lt;p style=&quot;border-color: var(--border-color); margin: 0px 0px 0px 4px;&quot;&gt;+ getDoor(): Door&lt;/p&gt;&lt;p style=&quot;border-color: var(--border-color); margin: 0px 0px 0px 4px;&quot;&gt;+&amp;nbsp;getDestinationFloors(): TreeSet&amp;lt;Integer&amp;gt;&lt;/p&gt;&lt;p style=&quot;border-color: var(--border-color); margin: 0px 0px 0px 4px;&quot;&gt;+&amp;nbsp;getSourceFloors(): TreeSet&amp;lt;Integer&amp;gt;&lt;br&gt;&lt;/p&gt;" style="verticalAlign=top;align=left;overflow=fill;fontSize=12;fontFamily=Helvetica;html=1;whiteSpace=wrap;" vertex="1" parent="1">
          <mxGeometry x="-780" y="-210" width="290" height="340" as="geometry" />
        </mxCell>
        <mxCell id="5X_Inju6__3TvkD5niFS-3" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;exitX=0.431;exitY=0.999;exitDx=0;exitDy=0;exitPerimeter=0;entryX=0;entryY=0;entryDx=0;entryDy=0;" edge="1" parent="1" source="7ej0JeDtoSVcsfH2DCIB-19" target="lApMr0xc0mvT29AV6tHW-3">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1730" y="200" as="sourcePoint" />
            <mxPoint x="-1570" y="200" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="5X_Inju6__3TvkD5niFS-4" value="" style="endArrow=diamondThin;endFill=0;endSize=24;html=1;rounded=0;elbow=vertical;entryX=0.5;entryY=0;entryDx=0;entryDy=0;exitX=0.425;exitY=1.003;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="5X_Inju6__3TvkD5niFS-2" target="7ej0JeDtoSVcsfH2DCIB-2">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1030" y="120" as="sourcePoint" />
            <mxPoint x="-1020" y="60" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="5X_Inju6__3TvkD5niFS-5" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;exitX=-0.01;exitY=0.804;exitDx=0;exitDy=0;exitPerimeter=0;entryX=1;entryY=0.5;entryDx=0;entryDy=0;" edge="1" parent="1" source="5X_Inju6__3TvkD5niFS-2" target="lApMr0xc0mvT29AV6tHW-6">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-950" y="200" as="sourcePoint" />
            <mxPoint x="-790" y="200" as="targetPoint" />
            <Array as="points">
              <mxPoint x="-1010" y="63" />
              <mxPoint x="-1010" y="670" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="5X_Inju6__3TvkD5niFS-6" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;GUI&lt;/b&gt;&lt;br&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;/p&gt;- &lt;u&gt;serialVersionUID: long&lt;/u&gt;&lt;div&gt;&lt;u&gt;- elevatorNum: int&lt;/u&gt;&lt;/div&gt;&lt;div&gt;&lt;u&gt;- floorNum: int&lt;/u&gt;&lt;/div&gt;&lt;div&gt;&lt;u&gt;- floors:&amp;nbsp;JLabel[][]&amp;nbsp;&lt;/u&gt;&lt;/div&gt;&lt;div&gt;&lt;u&gt;- floorTitles:&amp;nbsp;JLabel[][]&amp;nbsp;&lt;/u&gt;&lt;/div&gt;&lt;div&gt;&lt;u&gt;- elevInfos:&amp;nbsp;JLabel[][]&amp;nbsp;&lt;/u&gt;&lt;/div&gt;&lt;div&gt;&lt;u&gt;- floorDtoSocket:&amp;nbsp;UDPClient&amp;nbsp;&lt;/u&gt;&lt;/div&gt;&lt;div&gt;&lt;u&gt;- elevatorDtoSocket:&amp;nbsp;UDPClient&lt;/u&gt;&lt;/div&gt;&lt;hr size=&quot;1&quot;&gt;&amp;nbsp;+ GUI(SimulatorConfiguration): void&lt;br&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;- displayGUI(): void&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ handleElevatorEvent(ElevatorGuiData): void&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ handleFloorEvent(FloorGuiData): void&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ listenForFloorData(): void&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ listenForElevatorData(): void&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ run():void&lt;/p&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;+ &lt;u&gt;main(String[]):void&lt;/u&gt;&lt;/p&gt;" style="verticalAlign=top;align=left;overflow=fill;fontSize=12;fontFamily=Helvetica;html=1;whiteSpace=wrap;" vertex="1" parent="1">
          <mxGeometry x="-1310" y="-950" width="290" height="340" as="geometry" />
        </mxCell>
        <mxCell id="5X_Inju6__3TvkD5niFS-7" value="" style="endArrow=diamondThin;endFill=0;endSize=24;html=1;rounded=0;entryX=0.5;entryY=0;entryDx=0;entryDy=0;exitX=0;exitY=0.5;exitDx=0;exitDy=0;" edge="1" parent="1" source="5X_Inju6__3TvkD5niFS-6" target="7ej0JeDtoSVcsfH2DCIB-19">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1420" y="-540" as="sourcePoint" />
            <mxPoint x="-1260" y="-540" as="targetPoint" />
            <Array as="points">
              <mxPoint x="-1730" y="-780" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="5X_Inju6__3TvkD5niFS-8" value="" style="endArrow=diamondThin;endFill=0;endSize=24;html=1;rounded=0;exitX=1;exitY=0.5;exitDx=0;exitDy=0;entryX=0.5;entryY=0;entryDx=0;entryDy=0;" edge="1" parent="1" source="5X_Inju6__3TvkD5niFS-6" target="5X_Inju6__3TvkD5niFS-2">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-790" y="-730" as="sourcePoint" />
            <mxPoint x="-630" y="-730" as="targetPoint" />
            <Array as="points">
              <mxPoint x="-630" y="-780" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="5X_Inju6__3TvkD5niFS-9" value="&lt;p style=&quot;margin:0px;margin-top:4px;text-align:center;&quot;&gt;&lt;b&gt;LogConsole&lt;/b&gt;&lt;br&gt;&lt;/p&gt;&lt;hr size=&quot;1&quot;&gt;&lt;p style=&quot;margin:0px;margin-left:4px;&quot;&gt;&lt;/p&gt;- console: JTextArea&lt;br&gt;- title: String&lt;br&gt;&lt;hr size=&quot;1&quot;&gt;&amp;nbsp;- initCinsike(): void&lt;br&gt;+ appendLog(String):void" style="verticalAlign=top;align=left;overflow=fill;fontSize=12;fontFamily=Helvetica;html=1;whiteSpace=wrap;" vertex="1" parent="1">
          <mxGeometry x="-1610" y="1250" width="250" height="120" as="geometry" />
        </mxCell>
        <mxCell id="5X_Inju6__3TvkD5niFS-10" value="" style="endArrow=diamondThin;endFill=0;endSize=24;html=1;rounded=0;entryX=1.013;entryY=0.945;entryDx=0;entryDy=0;entryPerimeter=0;exitX=0.097;exitY=0.01;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="5X_Inju6__3TvkD5niFS-9" target="nDF-ZZZx2z4eVH97TIbB-8">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1680" y="910" as="sourcePoint" />
            <mxPoint x="-1520" y="910" as="targetPoint" />
            <Array as="points">
              <mxPoint x="-1690" y="780" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="5X_Inju6__3TvkD5niFS-11" value="" style="endArrow=diamondThin;endFill=0;endSize=24;html=1;rounded=0;exitX=1;exitY=0.25;exitDx=0;exitDy=0;entryX=0.004;entryY=0.949;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="5X_Inju6__3TvkD5niFS-9" target="7ej0JeDtoSVcsfH2DCIB-3">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1250" y="1280" as="sourcePoint" />
            <mxPoint x="-1090" y="1280" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="5X_Inju6__3TvkD5niFS-12" value="" style="endArrow=diamondThin;endFill=0;endSize=24;html=1;rounded=0;entryX=0;entryY=0.658;entryDx=0;entryDy=0;entryPerimeter=0;exitX=0.25;exitY=0;exitDx=0;exitDy=0;" edge="1" parent="1" source="5X_Inju6__3TvkD5niFS-9" target="7ej0JeDtoSVcsfH2DCIB-15">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="-1720" y="440" as="sourcePoint" />
            <mxPoint x="-1560" y="440" as="targetPoint" />
            <Array as="points">
              <mxPoint x="-1640" y="490" />
            </Array>
          </mxGeometry>
        </mxCell>
      </root>
    </mxGraphModel>
  </diagram>
  <diagram id="k9m8eY7wILDAw9S-2OAU" name="Elevator State Diagram">
    <mxGraphModel dx="853" dy="1103" grid="1" gridSize="10" guides="1" tooltips="1" connect="1" arrows="1" fold="1" page="1" pageScale="1" pageWidth="850" pageHeight="1100" math="0" shadow="0">
      <root>
        <mxCell id="0" />
        <mxCell id="1" parent="0" />
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-18" value="DOORS_OPEN" style="swimlane;childLayout=stackLayout;horizontal=1;startSize=30;horizontalStack=0;rounded=1;fontSize=14;fontStyle=0;strokeWidth=2;resizeParent=0;resizeLast=1;shadow=0;dashed=0;align=center;" parent="1" vertex="1">
          <mxGeometry x="375" y="355" width="310" height="190" as="geometry" />
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-19" value="entry/SetLoadingTimer();&#xa;        SetDoors(OPEN);&#xa;        LoadPassengers(externalRequests, currentFloor);&#xa;        UnloadPassengers(internalRequests, currentFloor);&#xa;&#xa;exit/KillTimer();" style="align=left;strokeColor=none;fillColor=none;spacingLeft=4;fontSize=12;verticalAlign=top;resizable=0;rotatable=0;part=1;" parent="SvOxdCtLK2RC6KqI_oHb-18" vertex="1">
          <mxGeometry y="30" width="310" height="160" as="geometry" />
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-24" value="" style="ellipse;fillColor=strokeColor;" parent="1" vertex="1">
          <mxGeometry x="195" y="465" width="30" height="30" as="geometry" />
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-26" value="DOORS_CLOSED" style="swimlane;childLayout=stackLayout;horizontal=1;startSize=30;horizontalStack=0;rounded=1;fontSize=14;fontStyle=0;strokeWidth=2;resizeParent=0;resizeLast=1;shadow=0;dashed=0;align=center;" parent="1" vertex="1">
          <mxGeometry x="580" y="775" width="160" height="140" as="geometry" />
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-27" value="entry/SetCloseDoorsTimer();&#xa;        SetDoors(CLOSED);&#xa;&#xa;exit/KillTimer();" style="align=left;strokeColor=none;fillColor=none;spacingLeft=4;fontSize=12;verticalAlign=top;resizable=0;rotatable=0;part=1;" parent="SvOxdCtLK2RC6KqI_oHb-26" vertex="1">
          <mxGeometry y="30" width="160" height="110" as="geometry" />
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-30" value="STOPPED" style="swimlane;childLayout=stackLayout;horizontal=1;startSize=30;horizontalStack=0;rounded=1;fontSize=14;fontStyle=0;strokeWidth=2;resizeParent=0;resizeLast=1;shadow=0;dashed=0;align=center;" parent="1" vertex="1">
          <mxGeometry x="940" y="470" width="180" height="150" as="geometry" />
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-31" value="entry/SetOpenDoorsTimer();&#xa;        SetMotor(IDLE);&#xa;&#xa;exit/KillTimer();" style="align=left;strokeColor=none;fillColor=none;spacingLeft=4;fontSize=12;verticalAlign=top;resizable=0;rotatable=0;part=1;" parent="SvOxdCtLK2RC6KqI_oHb-30" vertex="1">
          <mxGeometry y="30" width="180" height="120" as="geometry" />
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-33" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="SvOxdCtLK2RC6KqI_oHb-24" target="SvOxdCtLK2RC6KqI_oHb-49" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="475" y="410" as="sourcePoint" />
            <mxPoint x="345" y="420" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-35" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="SvOxdCtLK2RC6KqI_oHb-18" target="SvOxdCtLK2RC6KqI_oHb-40" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="573" y="412" as="sourcePoint" />
            <mxPoint x="600" y="695" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-36" value="CLOSE_DOORS" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="SvOxdCtLK2RC6KqI_oHb-35" vertex="1" connectable="0">
          <mxGeometry x="-0.1758" y="1" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-37" value="" style="endArrow=classic;html=1;rounded=0;elbow=vertical;" parent="1" source="SvOxdCtLK2RC6KqI_oHb-26" target="y7Xf-iG10D7MoEptl7xi-4" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="573" y="412" as="sourcePoint" />
            <mxPoint x="840" y="795" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-39" value="THROTTLE_MOTOR" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="SvOxdCtLK2RC6KqI_oHb-37" vertex="1" connectable="0">
          <mxGeometry x="-0.1967" y="2" relative="1" as="geometry">
            <mxPoint x="14" as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-40" value="" style="ellipse;" parent="1" vertex="1">
          <mxGeometry x="570" y="665" width="30" height="30" as="geometry" />
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-44" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="SvOxdCtLK2RC6KqI_oHb-40" target="SvOxdCtLK2RC6KqI_oHb-26" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="573" y="412" as="sourcePoint" />
            <mxPoint x="623" y="485" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-45" value="[internalRequests != empty and externalRequests != empty]" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="SvOxdCtLK2RC6KqI_oHb-44" vertex="1" connectable="0">
          <mxGeometry x="-0.2972" y="2" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-49" value="IDLE" style="swimlane;childLayout=stackLayout;horizontal=1;startSize=30;horizontalStack=0;rounded=1;fontSize=14;fontStyle=0;strokeWidth=2;resizeParent=0;resizeLast=1;shadow=0;dashed=0;align=center;" parent="1" vertex="1">
          <mxGeometry x="60" y="575" width="300" height="175" as="geometry" />
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-50" value="entry/SetDoors(OPEN);&#xa;        SetDirection(IDLE);&#xa;&#xa;exit/LoadPassengers(externalRequests, currentFloor)" style="align=left;strokeColor=none;fillColor=none;spacingLeft=4;fontSize=12;verticalAlign=top;resizable=0;rotatable=0;part=1;" parent="SvOxdCtLK2RC6KqI_oHb-49" vertex="1">
          <mxGeometry y="30" width="300" height="145" as="geometry" />
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-51" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="SvOxdCtLK2RC6KqI_oHb-40" target="SvOxdCtLK2RC6KqI_oHb-49" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="450" y="425" as="sourcePoint" />
            <mxPoint x="450" y="485" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-52" value="[else]" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="SvOxdCtLK2RC6KqI_oHb-51" vertex="1" connectable="0">
          <mxGeometry x="-0.2029" y="-1" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-53" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="BIOioSUbKdEULqfMsoLJ-11" target="b3Z6lbw62nyGcSL218pY-3" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="1190" y="785" as="sourcePoint" />
            <mxPoint x="1210" y="715" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="SvOxdCtLK2RC6KqI_oHb-55" value="STOP_MOTOR" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="SvOxdCtLK2RC6KqI_oHb-53" vertex="1" connectable="0">
          <mxGeometry x="-0.2457" y="-3" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="BIOioSUbKdEULqfMsoLJ-1" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="SvOxdCtLK2RC6KqI_oHb-30" target="brWMZWOyVO0aU5Z_C8D_-5" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="1030" y="775" as="sourcePoint" />
            <mxPoint x="910" y="435" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="BIOioSUbKdEULqfMsoLJ-2" value="OPEN_DOORS" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="BIOioSUbKdEULqfMsoLJ-1" vertex="1" connectable="0">
          <mxGeometry x="-0.2457" y="-3" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="BIOioSUbKdEULqfMsoLJ-4" value="" style="ellipse;" parent="1" vertex="1">
          <mxGeometry x="1090" y="835" width="30" height="30" as="geometry" />
        </mxCell>
        <mxCell id="BIOioSUbKdEULqfMsoLJ-11" value="MOVING_DOWN" style="swimlane;childLayout=stackLayout;horizontal=1;startSize=30;horizontalStack=0;rounded=1;fontSize=14;fontStyle=0;strokeWidth=2;resizeParent=0;resizeLast=1;shadow=0;dashed=0;align=center;" parent="1" vertex="1">
          <mxGeometry x="1410" y="725" width="220" height="170" as="geometry">
            <mxRectangle y="140" width="440" height="30" as="alternateBounds" />
          </mxGeometry>
        </mxCell>
        <mxCell id="BIOioSUbKdEULqfMsoLJ-12" value="entry/SetStopMotorTimer();&#xa;        SetMotor(DOWN);&#xa;&#xa;exit/KillTimer();&#xa;        SetCurrentFloor(currentFloor--);&#xa;        NotifyArrivalSensor(currentFloor);&#xa;        " style="align=left;strokeColor=none;fillColor=none;spacingLeft=4;fontSize=12;verticalAlign=top;resizable=0;rotatable=0;part=1;" parent="BIOioSUbKdEULqfMsoLJ-11" vertex="1">
          <mxGeometry y="30" width="220" height="140" as="geometry" />
        </mxCell>
        <mxCell id="BIOioSUbKdEULqfMsoLJ-7" value="MOVING_UP" style="swimlane;childLayout=stackLayout;horizontal=1;startSize=30;horizontalStack=0;rounded=1;fontSize=14;fontStyle=0;strokeWidth=2;resizeParent=0;resizeLast=1;shadow=0;dashed=0;align=center;" parent="1" vertex="1">
          <mxGeometry x="820" y="905" width="220" height="170" as="geometry" />
        </mxCell>
        <mxCell id="BIOioSUbKdEULqfMsoLJ-8" value="entry/SetStopMotorTimer();&#xa;        SetMotor(UP);&#xa;&#xa;exit/KillTimer();&#xa;        SetCurrentFloor(currentFloor++);&#xa;        NotifyArrivalSensor(currentFloor);" style="align=left;strokeColor=none;fillColor=none;spacingLeft=4;fontSize=12;verticalAlign=top;resizable=0;rotatable=0;part=1;" parent="BIOioSUbKdEULqfMsoLJ-7" vertex="1">
          <mxGeometry y="30" width="220" height="140" as="geometry" />
        </mxCell>
        <mxCell id="y7Xf-iG10D7MoEptl7xi-1" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="SvOxdCtLK2RC6KqI_oHb-49" target="SvOxdCtLK2RC6KqI_oHb-26" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="420" y="415" as="sourcePoint" />
            <mxPoint x="420" y="485" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="y7Xf-iG10D7MoEptl7xi-3" value="REQUEST_RECEIVED" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="y7Xf-iG10D7MoEptl7xi-1" vertex="1" connectable="0">
          <mxGeometry x="-0.3023" y="1" relative="1" as="geometry">
            <mxPoint x="5" y="13" as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="y7Xf-iG10D7MoEptl7xi-4" value="" style="ellipse;" parent="1" vertex="1">
          <mxGeometry x="880" y="740" width="30" height="30" as="geometry" />
        </mxCell>
        <mxCell id="y7Xf-iG10D7MoEptl7xi-5" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="y7Xf-iG10D7MoEptl7xi-4" target="SvOxdCtLK2RC6KqI_oHb-30" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="420" y="565" as="sourcePoint" />
            <mxPoint x="420" y="625" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="y7Xf-iG10D7MoEptl7xi-7" value="[externalRequest exists @ current floor]" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="y7Xf-iG10D7MoEptl7xi-5" vertex="1" connectable="0">
          <mxGeometry x="0.1587" y="-2" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="y7Xf-iG10D7MoEptl7xi-8" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="y7Xf-iG10D7MoEptl7xi-4" target="BIOioSUbKdEULqfMsoLJ-11" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="970" y="805" as="sourcePoint" />
            <mxPoint x="1020" y="825" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="y7Xf-iG10D7MoEptl7xi-9" value="[Direction==UP and there exists no internalRequests&lt;br&gt;or externalRequests above currentFloor]/&lt;br&gt;SetDirection(DOWN);&amp;nbsp;" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="y7Xf-iG10D7MoEptl7xi-8" vertex="1" connectable="0">
          <mxGeometry x="0.1587" y="-2" relative="1" as="geometry">
            <mxPoint x="32" y="1" as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b3Z6lbw62nyGcSL218pY-3" value="" style="ellipse;" parent="1" vertex="1">
          <mxGeometry x="1220" y="675" width="30" height="30" as="geometry" />
        </mxCell>
        <mxCell id="b3Z6lbw62nyGcSL218pY-4" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="b3Z6lbw62nyGcSL218pY-3" target="SvOxdCtLK2RC6KqI_oHb-30" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="400" y="1141" as="sourcePoint" />
            <mxPoint x="482" y="1092" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b3Z6lbw62nyGcSL218pY-5" value="[externalRequest or internalRequest exists @ currentFloor" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="b3Z6lbw62nyGcSL218pY-4" vertex="1" connectable="0">
          <mxGeometry x="0.2659" y="-1" relative="1" as="geometry">
            <mxPoint x="90" y="22" as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b3Z6lbw62nyGcSL218pY-7" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="y7Xf-iG10D7MoEptl7xi-4" target="BIOioSUbKdEULqfMsoLJ-7" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="1243" y="835" as="sourcePoint" />
            <mxPoint x="970" y="915" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b3Z6lbw62nyGcSL218pY-8" value="[else]/&lt;br&gt;SetDirection(UP)&lt;br&gt;FIXME..." style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="b3Z6lbw62nyGcSL218pY-7" vertex="1" connectable="0">
          <mxGeometry x="-0.2457" y="-3" relative="1" as="geometry">
            <mxPoint x="-11" y="17" as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b3Z6lbw62nyGcSL218pY-9" value="" style="endArrow=classic;html=1;rounded=0;edgeStyle=elbowEdgeStyle;" parent="1" source="b3Z6lbw62nyGcSL218pY-3" target="BIOioSUbKdEULqfMsoLJ-11" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="1181" y="810" as="sourcePoint" />
            <mxPoint x="1410" y="935" as="targetPoint" />
            <Array as="points">
              <mxPoint x="1510" y="745" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="b3Z6lbw62nyGcSL218pY-10" value="[else]" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="b3Z6lbw62nyGcSL218pY-9" vertex="1" connectable="0">
          <mxGeometry x="-0.2457" y="-3" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b3Z6lbw62nyGcSL218pY-13" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="BIOioSUbKdEULqfMsoLJ-7" target="BIOioSUbKdEULqfMsoLJ-4" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="1249" y="925" as="sourcePoint" />
            <mxPoint x="1245" y="715" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b3Z6lbw62nyGcSL218pY-14" value="STOP_MOTOR" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="b3Z6lbw62nyGcSL218pY-13" vertex="1" connectable="0">
          <mxGeometry x="-0.2457" y="-3" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b3Z6lbw62nyGcSL218pY-15" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="BIOioSUbKdEULqfMsoLJ-4" target="SvOxdCtLK2RC6KqI_oHb-30" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="1233" y="692" as="sourcePoint" />
            <mxPoint x="1110" y="609" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b3Z6lbw62nyGcSL218pY-16" value="[externalRequest or internalRequest exists @ currentFloor" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="b3Z6lbw62nyGcSL218pY-15" vertex="1" connectable="0">
          <mxGeometry x="0.2659" y="-1" relative="1" as="geometry">
            <mxPoint x="8" y="17" as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b3Z6lbw62nyGcSL218pY-18" value="" style="endArrow=classic;html=1;rounded=0;edgeStyle=elbowEdgeStyle;" parent="1" source="BIOioSUbKdEULqfMsoLJ-4" target="BIOioSUbKdEULqfMsoLJ-7" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="943" y="935" as="sourcePoint" />
            <mxPoint x="1130" y="955" as="targetPoint" />
            <Array as="points">
              <mxPoint x="1105" y="1005" />
              <mxPoint x="1070" y="825" />
              <mxPoint x="1080" y="975" />
              <mxPoint x="1060" y="985" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="b3Z6lbw62nyGcSL218pY-19" value="[else]" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="b3Z6lbw62nyGcSL218pY-18" vertex="1" connectable="0">
          <mxGeometry x="-0.2457" y="-3" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-1" value="DOORS_STUCK" style="swimlane;childLayout=stackLayout;horizontal=1;startSize=30;horizontalStack=0;rounded=1;fontSize=14;fontStyle=0;strokeWidth=2;resizeParent=0;resizeLast=1;shadow=0;dashed=0;align=center;" parent="1" vertex="1">
          <mxGeometry x="710" y="75" width="180" height="150" as="geometry" />
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-2" value="entry/SetDoorsStuckTimer();&#xa;        NotifyArrivalSensor();&#xa;&#xa;exit/KillTimer();" style="align=left;strokeColor=none;fillColor=none;spacingLeft=4;fontSize=12;verticalAlign=top;resizable=0;rotatable=0;part=1;" parent="brWMZWOyVO0aU5Z_C8D_-1" vertex="1">
          <mxGeometry y="30" width="180" height="120" as="geometry" />
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-3" value="ELEVATOR_STUCK" style="swimlane;childLayout=stackLayout;horizontal=1;startSize=30;horizontalStack=0;rounded=1;fontSize=14;fontStyle=0;strokeWidth=2;resizeParent=0;resizeLast=1;shadow=0;dashed=0;align=center;" parent="1" vertex="1">
          <mxGeometry x="1100" y="135" width="180" height="150" as="geometry" />
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-4" value="entry/NotifyArrivalSensor();&#xa;&#xa;exit/" style="align=left;strokeColor=none;fillColor=none;spacingLeft=4;fontSize=12;verticalAlign=top;resizable=0;rotatable=0;part=1;" parent="brWMZWOyVO0aU5Z_C8D_-3" vertex="1">
          <mxGeometry y="30" width="180" height="120" as="geometry" />
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-5" value="" style="ellipse;" parent="1" vertex="1">
          <mxGeometry x="880" y="355" width="30" height="30" as="geometry" />
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-6" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="brWMZWOyVO0aU5Z_C8D_-5" target="SvOxdCtLK2RC6KqI_oHb-18" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="982" y="480" as="sourcePoint" />
            <mxPoint x="914" y="392" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-7" value="[else]" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="brWMZWOyVO0aU5Z_C8D_-6" connectable="0" vertex="1">
          <mxGeometry x="-0.2457" y="-3" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-8" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="brWMZWOyVO0aU5Z_C8D_-5" target="brWMZWOyVO0aU5Z_C8D_-3" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="982" y="480" as="sourcePoint" />
            <mxPoint x="914" y="392" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-9" value="[if elevator stuck floor]" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="brWMZWOyVO0aU5Z_C8D_-8" connectable="0" vertex="1">
          <mxGeometry x="-0.2457" y="-3" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-10" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="brWMZWOyVO0aU5Z_C8D_-5" target="brWMZWOyVO0aU5Z_C8D_-1" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="918" y="373" as="sourcePoint" />
            <mxPoint x="1110" y="269" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-11" value="[else if door stuck floor]" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="brWMZWOyVO0aU5Z_C8D_-10" connectable="0" vertex="1">
          <mxGeometry x="-0.2457" y="-3" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-12" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="brWMZWOyVO0aU5Z_C8D_-1" target="SvOxdCtLK2RC6KqI_oHb-18" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="901" y="366" as="sourcePoint" />
            <mxPoint x="863" y="245" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-13" value="DOORS_OBSTRUCTED" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="brWMZWOyVO0aU5Z_C8D_-12" connectable="0" vertex="1">
          <mxGeometry x="-0.2457" y="-3" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-14" value="@ elevator stuck state, &quot;refund&quot; the remaining external requests back to the scheduler (as if the floor sent it to them)" style="text;html=1;align=center;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" parent="1" vertex="1">
          <mxGeometry x="910" y="25" width="640" height="30" as="geometry" />
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-15" value="" style="ellipse;html=1;shape=endState;fillColor=strokeColor;" parent="1" vertex="1">
          <mxGeometry x="1360" y="65" width="30" height="30" as="geometry" />
        </mxCell>
        <mxCell id="brWMZWOyVO0aU5Z_C8D_-16" value="" style="endArrow=classic;html=1;rounded=0;" parent="1" source="brWMZWOyVO0aU5Z_C8D_-3" target="brWMZWOyVO0aU5Z_C8D_-15" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="918" y="373" as="sourcePoint" />
            <mxPoint x="1110" y="269" as="targetPoint" />
          </mxGeometry>
        </mxCell>
      </root>
    </mxGraphModel>
  </diagram>
  <diagram id="zBsN5w9_e8Dvqzdttgen" name="Scheduler State Diagram">
    <mxGraphModel dx="1434" dy="699" grid="1" gridSize="10" guides="1" tooltips="1" connect="1" arrows="1" fold="1" page="1" pageScale="1" pageWidth="850" pageHeight="1100" math="0" shadow="0">
      <root>
        <mxCell id="0" />
        <mxCell id="1" parent="0" />
        <mxCell id="IUitYs0JlPA4rf3hk2v9-6" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;" parent="1" source="IUitYs0JlPA4rf3hk2v9-1" target="IUitYs0JlPA4rf3hk2v9-3" edge="1">
          <mxGeometry relative="1" as="geometry">
            <Array as="points">
              <mxPoint x="623" y="440" />
              <mxPoint x="623" y="440" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="IUitYs0JlPA4rf3hk2v9-7" value="PENDING_REQUEST_RECEIVED" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="IUitYs0JlPA4rf3hk2v9-6" connectable="0" vertex="1">
          <mxGeometry x="-0.1846" y="-3" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="IUitYs0JlPA4rf3hk2v9-1" value="IDLE" style="swimlane;childLayout=stackLayout;horizontal=1;startSize=30;horizontalStack=0;rounded=1;fontSize=14;fontStyle=0;strokeWidth=2;resizeParent=0;resizeLast=1;shadow=0;dashed=0;align=center;" parent="1" vertex="1">
          <mxGeometry x="463" y="100" width="190" height="160" as="geometry" />
        </mxCell>
        <mxCell id="IUitYs0JlPA4rf3hk2v9-2" value="entry/&#xa;&#xa;exit/" style="align=left;strokeColor=none;fillColor=none;spacingLeft=4;fontSize=12;verticalAlign=top;resizable=0;rotatable=0;part=1;" parent="IUitYs0JlPA4rf3hk2v9-1" vertex="1">
          <mxGeometry y="30" width="190" height="130" as="geometry" />
        </mxCell>
        <mxCell id="IUitYs0JlPA4rf3hk2v9-10" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;" parent="1" source="IUitYs0JlPA4rf3hk2v9-3" target="y0aD8zuRQhk-F_mn5agw-1" edge="1">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="433" y="410" as="targetPoint" />
            <Array as="points">
              <mxPoint x="438" y="510" />
              <mxPoint x="438" y="510" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="IUitYs0JlPA4rf3hk2v9-11" value="PENDING_REQUEST_RECEIVED" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="IUitYs0JlPA4rf3hk2v9-10" connectable="0" vertex="1">
          <mxGeometry x="0.1966" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="IUitYs0JlPA4rf3hk2v9-3" value="IN_SERVICE" style="swimlane;childLayout=stackLayout;horizontal=1;startSize=30;horizontalStack=0;rounded=1;fontSize=14;fontStyle=0;strokeWidth=2;resizeParent=0;resizeLast=1;shadow=0;dashed=0;align=center;" parent="1" vertex="1">
          <mxGeometry x="418" y="550" width="240" height="160" as="geometry" />
        </mxCell>
        <mxCell id="IUitYs0JlPA4rf3hk2v9-4" value="entry/findBestElevatorToAssignRequest()&#xa;&#xa;&#xa;exit/" style="align=left;strokeColor=none;fillColor=none;spacingLeft=4;fontSize=12;verticalAlign=top;resizable=0;rotatable=0;part=1;" parent="IUitYs0JlPA4rf3hk2v9-3" vertex="1">
          <mxGeometry y="30" width="240" height="130" as="geometry" />
        </mxCell>
        <mxCell id="y0aD8zuRQhk-F_mn5agw-2" value="" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;" parent="1" source="y0aD8zuRQhk-F_mn5agw-1" target="IUitYs0JlPA4rf3hk2v9-2" edge="1">
          <mxGeometry relative="1" as="geometry">
            <Array as="points">
              <mxPoint x="433" y="195" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="y0aD8zuRQhk-F_mn5agw-4" value="[if pendingRequests is empty]" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="y0aD8zuRQhk-F_mn5agw-2" connectable="0" vertex="1">
          <mxGeometry x="-0.4055" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="y0aD8zuRQhk-F_mn5agw-3" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;exitX=0;exitY=0.5;exitDx=0;exitDy=0;" parent="1" source="y0aD8zuRQhk-F_mn5agw-1" target="IUitYs0JlPA4rf3hk2v9-3" edge="1">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="263" y="690" as="targetPoint" />
            <Array as="points">
              <mxPoint x="193" y="385" />
              <mxPoint x="193" y="690" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="MccTU9hhwAncdUVs01zO-1" value="[else]" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" parent="y0aD8zuRQhk-F_mn5agw-3" connectable="0" vertex="1">
          <mxGeometry x="-0.5984" y="-2" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="y0aD8zuRQhk-F_mn5agw-1" value="" style="ellipse;" parent="1" vertex="1">
          <mxGeometry x="423" y="370" width="30" height="30" as="geometry" />
        </mxCell>
        <mxCell id="0PU3ty-mZIM_Ss24ksAB-6" value="class InServiceState:&lt;br&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;constructor()&lt;br&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;findBestElevatorToAssignRequest()&lt;br&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;handleRequestReceived()&lt;br&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;return this;&lt;br&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;handleRequestSent()&lt;br&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;if not pending is not empty:&lt;br&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;return new InServiceState()&lt;br&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;return new IdleState()&lt;br&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" parent="1" vertex="1">
          <mxGeometry x="453" y="780" width="270" height="160" as="geometry" />
        </mxCell>
        <mxCell id="kjdczT2a2ZDZ1Rq8Nn25-1" value="&lt;div style=&quot;text-align: left;&quot;&gt;&lt;span style=&quot;background-color: initial;&quot;&gt;class SchedulerContext:&lt;/span&gt;&lt;/div&gt;&lt;div style=&quot;text-align: left;&quot;&gt;&lt;span style=&quot;background-color: initial;&quot;&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;&lt;br&gt;&lt;/span&gt;&lt;/div&gt;&lt;div style=&quot;text-align: left;&quot;&gt;&lt;span style=&quot;background-color: initial;&quot;&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;onPendingRequestReceived():&lt;br&gt;&lt;/span&gt;&lt;/div&gt;&lt;div style=&quot;text-align: left;&quot;&gt;&lt;span style=&quot;background-color: initial;&quot;&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;// lock behaviour&lt;br&gt;&lt;/span&gt;&lt;/div&gt;&lt;div style=&quot;text-align: left;&quot;&gt;&lt;span style=&quot;background-color: initial;&quot;&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;pendingReqState.handleRequestReceived()&lt;/span&gt;&lt;/div&gt;&lt;div style=&quot;text-align: left;&quot;&gt;&lt;span style=&quot;background-color: initial;&quot;&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;onCompletedRequestReceived():&lt;br&gt;&lt;/span&gt;&lt;/div&gt;&lt;div style=&quot;text-align: left;&quot;&gt;&lt;span style=&quot;background-color: initial;&quot;&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;// lock behaviour&lt;br&gt;&lt;/span&gt;&lt;/div&gt;&lt;div style=&quot;text-align: left;&quot;&gt;&lt;span style=&quot;background-color: initial;&quot;&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;&lt;span style=&quot;white-space: pre;&quot;&gt; &lt;/span&gt;completedReqState.handleRequestReceived()&lt;br&gt;&lt;/span&gt;&lt;/div&gt;&lt;div style=&quot;text-align: left;&quot;&gt;&lt;span style=&quot;background-color: initial;&quot;&gt;&lt;br&gt;&lt;/span&gt;&lt;/div&gt;" style="text;html=1;align=center;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" parent="1" vertex="1">
          <mxGeometry x="73" y="765" width="320" height="140" as="geometry" />
        </mxCell>
      </root>
    </mxGraphModel>
  </diagram>
  <diagram id="lLrxNBaU4XJXNC-x1QGt" name="Sequence Diagram">
    <mxGraphModel dx="1434" dy="699" grid="1" gridSize="10" guides="1" tooltips="1" connect="1" arrows="1" fold="1" page="1" pageScale="1" pageWidth="850" pageHeight="1100" math="0" shadow="0">
      <root>
        <mxCell id="0" />
        <mxCell id="1" parent="0" />
        <mxCell id="KZChgTnWSoUKDIBur-fi-1" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" source="KZChgTnWSoUKDIBur-fi-2" target="KZChgTnWSoUKDIBur-fi-7">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="693.5" y="55" as="sourcePoint" />
            <mxPoint x="693.5" y="-15" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-2" value="" style="rounded=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="683.5" y="320" width="20" height="75" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-3" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" source="KZChgTnWSoUKDIBur-fi-2" target="KZChgTnWSoUKDIBur-fi-44">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="818.5" y="587.5" as="sourcePoint" />
            <mxPoint x="694" y="939.5" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-4" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" source="KZChgTnWSoUKDIBur-fi-5" target="KZChgTnWSoUKDIBur-fi-8">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="68.5" y="60" as="sourcePoint" />
            <mxPoint x="68.5" y="-10" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-5" value="" style="rounded=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="58.5" y="100" width="20" height="190" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-6" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" source="KZChgTnWSoUKDIBur-fi-5" target="KZChgTnWSoUKDIBur-fi-28">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="193.5" y="110" as="sourcePoint" />
            <mxPoint x="66" y="797" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-7" value=":Elevator" style="shape=process;whiteSpace=wrap;html=1;backgroundOutline=1;" vertex="1" parent="1">
          <mxGeometry x="651" y="40" width="85" height="30" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-8" value=":Floor" style="shape=process;whiteSpace=wrap;html=1;backgroundOutline=1;" vertex="1" parent="1">
          <mxGeometry x="26" y="40" width="85" height="30" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-9" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" source="KZChgTnWSoUKDIBur-fi-10" target="KZChgTnWSoUKDIBur-fi-11">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="379.75" y="55" as="sourcePoint" />
            <mxPoint x="379.75" y="-15" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-10" value="" style="rounded=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="369.75" y="260" width="17.5" height="80" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-11" value=":Scheduler" style="shape=process;whiteSpace=wrap;html=1;backgroundOutline=1;" vertex="1" parent="1">
          <mxGeometry x="336" y="40" width="85" height="30" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-12" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" source="KZChgTnWSoUKDIBur-fi-10" target="KZChgTnWSoUKDIBur-fi-41">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="224.75" y="345" as="sourcePoint" />
            <mxPoint x="376" y="797" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-13" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" source="KZChgTnWSoUKDIBur-fi-14" target="KZChgTnWSoUKDIBur-fi-16">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="248.5" y="140" as="sourcePoint" />
            <mxPoint x="248.5" y="70" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-14" value="" style="rounded=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="238.5" y="140" width="20" height="70" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-15" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" source="KZChgTnWSoUKDIBur-fi-14">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="93.5" y="180" as="sourcePoint" />
            <mxPoint x="249" y="240" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-16" value=":Parser" style="html=1;" vertex="1" parent="1">
          <mxGeometry x="206" y="40" width="85" height="30" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-17" value="requestParser()" style="html=1;verticalAlign=bottom;endArrow=block;rounded=0;" edge="1" parent="1">
          <mxGeometry width="80" relative="1" as="geometry">
            <mxPoint x="78.5" y="149.8" as="sourcePoint" />
            <mxPoint x="236" y="150" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-18" value="return&lt;br&gt;ArrayList&amp;lt;ElevatorRequest&amp;gt;" style="html=1;verticalAlign=bottom;endArrow=open;dashed=1;endSize=8;rounded=0;" edge="1" parent="1">
          <mxGeometry x="0.0159" relative="1" as="geometry">
            <mxPoint x="238.5" y="200" as="sourcePoint" />
            <mxPoint x="81" y="200" as="targetPoint" />
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-19" value="floorSendData(:data)" style="html=1;verticalAlign=bottom;endArrow=block;rounded=0;" edge="1" parent="1">
          <mxGeometry x="0.0365" width="80" relative="1" as="geometry">
            <mxPoint x="78.50000000000006" y="277.26" as="sourcePoint" />
            <mxPoint x="366" y="277" as="targetPoint" />
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-20" value="&lt;i style=&quot;border-color: var(--border-color); font-size: 12px;&quot;&gt;«&lt;/i&gt;&lt;span style=&quot;border-color: var(--border-color); font-size: 12px;&quot;&gt;reply&lt;/span&gt;&lt;i style=&quot;border-color: var(--border-color); font-size: 12px;&quot;&gt;»&lt;/i&gt;" style="html=1;verticalAlign=bottom;endArrow=open;dashed=1;endSize=8;rounded=0;" edge="1" parent="1">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="366" y="330" as="sourcePoint" />
            <mxPoint x="78.5" y="330" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-22" value="floorAck(:ack)" style="html=1;verticalAlign=bottom;endArrow=block;rounded=0;" edge="1" parent="1">
          <mxGeometry x="0.0365" width="80" relative="1" as="geometry">
            <mxPoint x="80" y="650" as="sourcePoint" />
            <mxPoint x="369.75" y="650" as="targetPoint" />
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-23" value="&lt;i style=&quot;border-color: var(--border-color); font-size: 12px;&quot;&gt;«&lt;/i&gt;&lt;span style=&quot;border-color: var(--border-color); font-size: 12px;&quot;&gt;reply&lt;/span&gt;&lt;i style=&quot;border-color: var(--border-color); font-size: 12px;&quot;&gt;»&lt;/i&gt;" style="html=1;verticalAlign=bottom;endArrow=open;dashed=1;endSize=8;rounded=0;" edge="1" parent="1">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="369.75" y="720" as="sourcePoint" />
            <mxPoint x="80" y="720" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-24" value="elevatorSend()" style="html=1;verticalAlign=bottom;endArrow=block;rounded=0;" edge="1" parent="1">
          <mxGeometry x="0.0365" width="80" relative="1" as="geometry">
            <mxPoint x="680" y="390" as="sourcePoint" />
            <mxPoint x="387.25000000000006" y="390.26" as="targetPoint" />
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-25" value="&lt;i style=&quot;border-color: var(--border-color); font-size: 12px;&quot;&gt;«&lt;/i&gt;&lt;span style=&quot;border-color: var(--border-color); font-size: 12px;&quot;&gt;reply&lt;/span&gt;&lt;i style=&quot;border-color: var(--border-color); font-size: 12px;&quot;&gt;»&lt;/i&gt;" style="html=1;verticalAlign=bottom;endArrow=open;dashed=1;endSize=8;rounded=0;" edge="1" parent="1">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="387.25" y="450" as="sourcePoint" />
            <mxPoint x="680" y="450" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-26" value="elevatorSendAck(:ack)" style="html=1;verticalAlign=bottom;endArrow=block;rounded=0;entryX=1.157;entryY=0.128;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" target="KZChgTnWSoUKDIBur-fi-42">
          <mxGeometry x="0.0392" width="80" relative="1" as="geometry">
            <mxPoint x="683.5" y="510" as="sourcePoint" />
            <mxPoint x="396.00000000000006" y="510.26" as="targetPoint" />
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-27" value="&lt;i style=&quot;border-color: var(--border-color); font-size: 12px;&quot;&gt;«&lt;/i&gt;&lt;span style=&quot;border-color: var(--border-color); font-size: 12px;&quot;&gt;reply&lt;/span&gt;&lt;i style=&quot;border-color: var(--border-color); font-size: 12px;&quot;&gt;»&lt;/i&gt;" style="html=1;verticalAlign=bottom;endArrow=open;dashed=1;endSize=8;rounded=0;" edge="1" parent="1">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="387.25" y="573" as="sourcePoint" />
            <mxPoint x="680" y="573" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-28" value="" style="rounded=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="57.25" y="320" width="20" height="60" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-29" value="replyFloor()" style="text;html=1;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="395" y="285" width="60" height="30" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-30" value="replyElevator(:data)" style="text;html=1;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="280" y="410" width="60" height="30" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-31" value="ackElevator()" style="text;html=1;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="294.75" y="525" width="60" height="30" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-32" value="ackFloor(:ack)" style="text;html=1;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="400" y="667.5" width="60" height="30" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-33" value="" style="rounded=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="58.5" y="600" width="17.5" height="57.5" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-34" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" source="KZChgTnWSoUKDIBur-fi-28" target="KZChgTnWSoUKDIBur-fi-33">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="131" y="390" as="sourcePoint" />
            <mxPoint x="130" y="440" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-35" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" source="KZChgTnWSoUKDIBur-fi-33" target="KZChgTnWSoUKDIBur-fi-40">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="139.75" y="580" as="sourcePoint" />
            <mxPoint x="138.75" y="630" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-36" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" source="KZChgTnWSoUKDIBur-fi-43">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="694" y="757.5" as="sourcePoint" />
            <mxPoint x="693" y="760" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-37" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" source="KZChgTnWSoUKDIBur-fi-42" target="KZChgTnWSoUKDIBur-fi-46">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="161" y="420" as="sourcePoint" />
            <mxPoint x="380" y="700" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-38" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" source="KZChgTnWSoUKDIBur-fi-40">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="169.75" y="610" as="sourcePoint" />
            <mxPoint x="68" y="840" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-39" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" source="KZChgTnWSoUKDIBur-fi-41" target="KZChgTnWSoUKDIBur-fi-42">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="181" y="440" as="sourcePoint" />
            <mxPoint x="180" y="490" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-40" value="" style="rounded=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="59.75" y="710" width="17.5" height="60" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-41" value="" style="rounded=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="369.75" y="380" width="17.5" height="80" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-42" value="" style="rounded=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="369.75" y="500" width="17.5" height="80" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-43" value="" style="rounded=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="683.5" y="567.5" width="20" height="75" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-44" value="" style="rounded=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="683.5" y="440" width="20" height="75" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-45" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" source="KZChgTnWSoUKDIBur-fi-44" target="KZChgTnWSoUKDIBur-fi-43">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="703.5" y="687.5" as="sourcePoint" />
            <mxPoint x="702.5" y="737.5" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-46" value="" style="rounded=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="369.75" y="642.5" width="17.5" height="80" as="geometry" />
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-47" value="" style="endArrow=none;dashed=1;html=1;rounded=0;" edge="1" parent="1" target="KZChgTnWSoUKDIBur-fi-46">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="379" y="840" as="sourcePoint" />
            <mxPoint x="389" y="728" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="KZChgTnWSoUKDIBur-fi-48" value="elevatorSendAck(:ack)" style="text;html=1;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="736" y="462.5" width="60" height="30" as="geometry" />
        </mxCell>
      </root>
    </mxGraphModel>
  </diagram>
</mxfile>
