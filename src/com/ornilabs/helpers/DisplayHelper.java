package com.ornilabs.helpers;

import java.awt.Component;

public class DisplayHelper {
	public static void messageBox(String message) {
		messageBox(null,message);
	}

	public static void messageBox(Component object, String message) {
		javax.swing.JOptionPane.showMessageDialog(object,message); 
	}
}
