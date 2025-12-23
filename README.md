## KeimenoUI

A terminal-based Java library for creating rich text-based UIs, featuring ANSI colors, text alignment, toggles, and image-to-ASCII conversion. Perfect for developers building visually appealing console applications quickly.

⸻

## 📜 Version History ##
- v1-alpha
  - 🟢 Initial release with core text UI functionality.
  - ✨ Supports ANSI colors, bold/underline, and formatting toggles.
  - ↔️ Text alignment options: left, center, right.
  - 🖼 Added image-to-ASCII conversion.
  - 🛠 Usable for prototyping terminal UIs and experimenting with console visuals.

To download the .jar file use this command: 
	`wget https://github.com/Isht-Wow/keimenoUI/releases/download/alpha/v1-alpha-keimenoUI.jar`

Usage: 
	
	import filename.TUIHandler;
	import filename.TextFormatter;
	import filename.FormatterSettings;
	import filename.ImageFormatter;
	import java.io.IOException;
	
	public class App {
	    public static void main(String[] args) {
	        // Reset UI buffer
	        TUIHandler.newUI();
	
	        // ========================
	        // TEST HEADERS
	        // ========================
	        TextFormatter.header("WELCOME TO TUI SYSTEM", '=', FormatterSettings.CYAN);
	        TextFormatter.header("Multi-line Wrapped Header Test", '*', FormatterSettings.YELLOW, FormatterSettings.MAGENTA);
	        TextFormatter.header("Short Header");
	
	        // ========================
	        // TEST LONG TEXT WRAPPING
	        // ========================
	        String longText = "This is a very long string that should be automatically wrapped by the warpText function " +
	                "to ensure that it never exceeds the default width of the TUI formatter and aligns nicely. " +
	                "It also tests ANSI color handling and left/right/center alignment for extremely long inputs.";
	
	        // Center alignment
	        TextFormatter.centerFormatting(longText);
	
	        // Left alignment
	        TextFormatter.leftFormatting(longText, '-', FormatterSettings.GREEN, FormatterSettings.WHITE, FormatterSettings.defaultWidth, "|");
	
	        // Right alignment
	        TextFormatter.rightFormatting(longText, '.', FormatterSettings.MAGENTA, FormatterSettings.WHITE, FormatterSettings.defaultWidth, "|");
	
	        // ========================
	        // TEST PRINT OPTIONS
	        // ========================
	        String[] options = {"Start Game", "Load Game", "Settings", "Exit"};
	        String[] contentColours = {FormatterSettings.RED, FormatterSettings.GREEN, FormatterSettings.YELLOW, FormatterSettings.CYAN};
	        String[] fillColours = {FormatterSettings.WHITE, FormatterSettings.WHITE, FormatterSettings.WHITE, FormatterSettings.WHITE};
	
	        TextFormatter.printOptions(options, contentColours, fillColours);
	
	        // ========================
	        // TEST IMAGE TO ASCII
	        // ========================
	        try {
	            // Adjust path and width/height as needed
	            ImageFormatter.AsciiArt("resources/test.jpg", FormatterSettings.defaultWidth, FormatterSettings.defaultWidth, true);
	        } catch (IOException e) {
	            TextFormatter.header("Image test skipped (file not found)", '-', FormatterSettings.RED);
	        }
	
	        // ========================
	        // APPLY BUFFER
	        // ========================
	        TUIHandler.applyUI();
	
	        // ========================
	        // END TEST
	        // ========================
	        TextFormatter.header("ALL FUNCTIONS TEST COMPLETED", '=', FormatterSettings.CYAN);
	    }
    }
⸻

## 🛠 Project Details
-	Written in plain Java.
-	No build tools required (Maven, Gradle, or ANT optional).
-	Source Code is compatible with Java 8 and above; compiled and tested for Java 25.
-	Fully terminal-based, focusing on flexible text-based UI elements.
-	Supports toggleable formatting, letting developers dynamically enable/disable styling.
-	Includes ASCII conversion of images for creative terminal output.

⸻

🔮 Future Roadmap
    -	Expand text styling options (colors, gradients, backgrounds).
	-	Add interactive menus, buttons, headers/footers.
	-	Improve image-to-ASCII conversion quality.
	-	Provide utilities for spacing, tables, and layouts.
	-	Enhance cross-platform ANSI support, including Windows terminals.
	-	Publish detailed documentation and example projects for easier adoption.

⸻

## 📆 Versioning Convention
-	Repository creation: 23 December 2025
-	Versions follow semantic-inspired format: v[major]-[stage]
-	Example: v1-alpha

⸻

📣 Contact & Support

This is an early release. Feedback and contributions are welcome.
Please allow up to one week for responses to issues or pull requests.
