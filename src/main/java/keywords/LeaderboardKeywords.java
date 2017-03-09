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

import edu.jsu.mcis.*;

public class LeaderboardKeywords {

	@RobotKeyword("Clicks the leaderboard bar given by index.\n")
	@ArgumentNames({"index"})
	public void clickLeaderboardBarAtIndex(int index) {
		ContainerOperator context = (ContainerOperator) Context.getContext();
		ComponentChooser chooser = new LeaderboardChooser();
		JComponentOperator operator = new JComponentOperator(context, chooser);
		Leaderboard leaderboard = (Leaderboard)operator.getSource();
		Shape[] shapes = leaderboard.getShapes();
		if(index >= 0 && index < shapes.length) {
			Rectangle bounds = shapes[index].getBounds();
			operator.clickMouse(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2, 1);
		}
	}

	class LeaderboardChooser implements ComponentChooser {
		public LeaderboardChooser() {}
		public boolean checkComponent(Component comp) {
			return (comp instanceof Leaderboard);
		}
		public String getDescription() {
			return "Any Leaderboard";
		}
	}    
}