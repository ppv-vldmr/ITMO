# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.17

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Disable VCS-based implicit rules.
% : %,v


# Disable VCS-based implicit rules.
% : RCS/%


# Disable VCS-based implicit rules.
% : RCS/%,v


# Disable VCS-based implicit rules.
% : SCCS/s.%


# Disable VCS-based implicit rules.
% : s.%


.SUFFIXES: .hpux_make_needs_suffix_list


# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake

# The command to remove a file.
RM = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "/Users/vladimir/Desktop/lab-2/C. Паросочетание максимального веса"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "/Users/vladimir/Desktop/lab-2/C. Паросочетание максимального веса/cmake-build-debug"

# Include any dependencies generated for this target.
include CMakeFiles/C____.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/C____.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/C____.dir/flags.make

CMakeFiles/C____.dir/main.cpp.o: CMakeFiles/C____.dir/flags.make
CMakeFiles/C____.dir/main.cpp.o: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/Users/vladimir/Desktop/lab-2/C. Паросочетание максимального веса/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/C____.dir/main.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/C____.dir/main.cpp.o -c "/Users/vladimir/Desktop/lab-2/C. Паросочетание максимального веса/main.cpp"

CMakeFiles/C____.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/C____.dir/main.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/Users/vladimir/Desktop/lab-2/C. Паросочетание максимального веса/main.cpp" > CMakeFiles/C____.dir/main.cpp.i

CMakeFiles/C____.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/C____.dir/main.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/Users/vladimir/Desktop/lab-2/C. Паросочетание максимального веса/main.cpp" -o CMakeFiles/C____.dir/main.cpp.s

# Object files for target C____
C_____OBJECTS = \
"CMakeFiles/C____.dir/main.cpp.o"

# External object files for target C____
C_____EXTERNAL_OBJECTS =

C____: CMakeFiles/C____.dir/main.cpp.o
C____: CMakeFiles/C____.dir/build.make
C____: CMakeFiles/C____.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir="/Users/vladimir/Desktop/lab-2/C. Паросочетание максимального веса/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable C____"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/C____.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/C____.dir/build: C____

.PHONY : CMakeFiles/C____.dir/build

CMakeFiles/C____.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/C____.dir/cmake_clean.cmake
.PHONY : CMakeFiles/C____.dir/clean

CMakeFiles/C____.dir/depend:
	cd "/Users/vladimir/Desktop/lab-2/C. Паросочетание максимального веса/cmake-build-debug" && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" "/Users/vladimir/Desktop/lab-2/C. Паросочетание максимального веса" "/Users/vladimir/Desktop/lab-2/C. Паросочетание максимального веса" "/Users/vladimir/Desktop/lab-2/C. Паросочетание максимального веса/cmake-build-debug" "/Users/vladimir/Desktop/lab-2/C. Паросочетание максимального веса/cmake-build-debug" "/Users/vladimir/Desktop/lab-2/C. Паросочетание максимального веса/cmake-build-debug/CMakeFiles/C____.dir/DependInfo.cmake" --color=$(COLOR)
.PHONY : CMakeFiles/C____.dir/depend

