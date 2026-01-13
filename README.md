## KeimenoUI

A beginner-friendly library that makes building text-based interfaces easy and fun for hobbyists, game developers, and creative coders.

⸻

## 📜 Version History ##
- Versions till v2-beta
  - Initial release with core text UI functionality.
  - Supports ANSI colors, bold/underline, and formatting toggles.
  - Text alignment options: left, center, right.
  - Added image-to-ASCII conversion.
  - Inconsistent API. Many features may be irritating/buggy
  - Usable for prototyping terminal UIs and experimenting with console visuals.
  - Compatible with Java 11 and above.

- v2-stable
  -	🔧 Introduced central, mutable, synchronized CoreState for all formatting, display, and audio operations.
  -	 🖥 Added advanced formatting options: tables, list menus, and customizable headers.
  -	 ⌨ Implemented Linux-only InputManager using /dev/tty for raw per-character input handling.
  -	 🎨 Full ANSI color support for text, padding, borders, and ASCII rendering.
  -	 🔄 Dynamic display rewrite with buffered output, line wrapping, and redraw control.
  -	 🔊 Audio playback support integrated with CoreState for seamless sound handling.
  -	 🖼 Image-to-ASCII display with aspect ratio preservation and color support.
  -	 🎥 Video frame playback in ASCII format with frame rate control.
  -	 📦 Exposed a strong, frozen API for stable integration into other projects.
  -	 ☕ Support for Java 8 and above.

Usage: 

  	`
	import wow.ishit.version_keimenoUI.TUIHandler;
	import wow.ishit.version_keimenoUI.TextFormatter;
	iimport wow.ishit.version_keimenoUI..FormatterSettings;
	import wow.ishit.version_keimenoUI.ImageFormatter;
	import wow.ishit.version_keimenoUI.IOException;
	`
	
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
