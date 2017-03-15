package keywords;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.swing.context.Context;

import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.ContainerOperator;
import org.netbeans.jemmy.ComponentChooser;
import java.awt.Component;
import java.awt.Shape;
import java.awt.Rectangle;
import java.util.*;

import org.jfree.chart.*;
import org.jfree.chart.entity.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;


import edu.jsu.mcis.*;

public class LeaderboardKeywords {

	@RobotKeyword("Clicks the leaderboard bar given by index.\n")
	@ArgumentNames({"index"})
	public void clickLeaderboardBarAtIndex(int index) {
		ContainerOperator context = (ContainerOperator) Context.getContext();
		ComponentChooser chooser = new LeaderboardChooser();
		JComponentOperator operator = new JComponentOperator(context, chooser);
		ChartPanel leaderboard = (ChartPanel)operator.getSource();
		System.out.println("Clicking index " + index);
		EntityCollection entities = leaderboard.getChartRenderingInfo().getEntityCollection();
		java.util.List<Shape> shapes = new ArrayList<>();
		for(int i = 2; i < entities.getEntityCount(); i++) {
			ChartEntity e = entities.getEntity(i);
			if(e instanceof CategoryItemEntity) {
				String coords = e.getShapeCoords();
				System.out.println(coords);
				java.util.Scanner scanner = new java.util.Scanner(coords);
				scanner.useDelimiter(",");
				int ulx = scanner.nextInt();
				int uly = scanner.nextInt();
				int lrx = scanner.nextInt();
				int lry = scanner.nextInt();
				shapes.add(new Rectangle(ulx, uly, lrx - ulx, lry - uly));
			}
		}
		
		if(index >= 0 && index < shapes.size()) {
			Rectangle bounds = shapes.get(index).getBounds();
			System.out.println("Bounds are " + bounds);
			//operator.clickMouse(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2, 1);
			operator.clickMouse(237, 337, 1);
		}
	}

	class LeaderboardChooser implements ComponentChooser {
		public LeaderboardChooser() {}
		public boolean checkComponent(Component comp) {
			return (comp instanceof ChartPanel);
		}
		public String getDescription() {
			return "Any Leaderboard";
		}
	}    
}