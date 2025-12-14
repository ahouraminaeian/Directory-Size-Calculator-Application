# Directory-Size-Calculator-Application
This project implements a Directory Size Calculator that simulates a hierarchical file system consisting of directories and files. Each file has a size attribute, and directories can contain files and subdirectories. The application calculates the total size of a directory recursively.

Approach & Design:
- Used OOP principles
- Implemented an abstract base class (FileSystemItem) to represent shared behavior
- Used inheritance and polymorphism to distinguish between files and directories
- Applied recursion to compute directory sizes efficiently

Key Files & Folders:
- DirectorySizeCalculator.java – Main driver program
- Directory.java - Represents directories that contain files or subdirectories
- File.java - Represents files with fixed sizes
- FileSystemItem.java - Abstract base class for file system objects

Process to Run, Test, & Verify:
- Compile all Java files
- Run DirectorySizeCalculator
- The program initializes sample data and outputs the total directory size

Test/Seed Data:
- Test data is seeded directly in the main method with sample files and nested directories.

Verification:
- The output verifies correct recursive size calculation across nested directory structures.

**Built & Tested in Apache NetBeans (JDK25).**

###Source Code All application source code is included in the src/directorysizecalculator directory.
A ZIP archive is provided for convenience.
