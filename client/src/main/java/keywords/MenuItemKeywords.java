package keywords;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.swing.context.Context;

import org.netbeans.jemmy.operators.JRadioButtonMenuItemOperator;
import org.netbeans.jemmy.operators.ContainerOperator;
import javax.swing.JRadioButtonMenuItem;

public class MenuItemKeywords {

	@RobotKeyword("Returns whether the given radio button menu item is selected.\n")
	@ArgumentNames({"menuItemText"})
	public boolean radioButtonMenuItemIsSelected(String menuItemText) {
		ContainerOperator context = (ContainerOperator)Context.getContext();
		JRadioButtonMenuItemOperator operator = new JRadioButtonMenuItemOperator(context, menuItemText);
		JRadioButtonMenuItem item = (JRadioButtonMenuItem)operator.getSource();
		return item.isSelected();
	}

}
