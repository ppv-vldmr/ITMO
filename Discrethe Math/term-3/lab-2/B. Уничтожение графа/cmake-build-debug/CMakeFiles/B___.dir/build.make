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
CMAKE_SOURCE_DIR = "/Users/vladimir/Desktop/lab-2/B. Уничтожение графа"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "/Users/vladimir/Desktop/lab-2/B. Уничтожение графа/cmake-build-debug"

# Include any dependencies generated for this target.
include CMakeFiles/B___.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/B___.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/B___.dir/flags.make

CMakeFiles/B___.dir/main.cpp.o: CMakeFiles/B___.dir/flags.make
CMakeFiles/B___.dir/main.cpp.o: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/Users/vladimir/Desktop/lab-2/B. Уничтожение графа/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/B___.dir/main.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/B___.dir/main.cpp.o -c "/Users/vladimir/Desktop/lab-2/B. Уничтожение графа/main.cpp"

CMakeFiles/B___.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/B___.dir/main.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/Users/vladimir/Desktop/lab-2/B. Уничтожение графа/main.cpp" > CMakeFiles/B___.dir/main.cpp.i

CMakeFiles/B___.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/B___.dir/main.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/Users/vladimir/Desktop/lab-2/B. Уничтожение графа/main.cpp" -o CMakeFiles/B___.dir/main.cpp.s

# Object files for target B___
B____OBJECTS = \
"CMakeFiles/B___.dir/main.cpp.o"

# External object files for target B___
B____EXTERNAL_OBJECTS =

B___: CMakeFiles/B___.dir/main.cpp.o
B___: CMakeFiles/B___.dir/build.make
B___: CMakeFiles/B___.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir="/Users/vladimir/Desktop/lab-2/B. Уничтожение графа/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable B___"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/B___.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/B___.dir/build: B___

.PHONY : CMakeFiles/B___.dir/build

CMakeFiles/B___.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/B___.dir/cmake_clean.cmake
.PHONY : CMakeFiles/B___.dir/clean

CMakeFiles/B___.dir/depend:
	cd "/Users/vladimir/Desktop/lab-2/B. Уничтожение графа/cmake-build-debug" && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" "/Users/vladimir/Desktop/lab-2/B. Уничтожение графа" "/Users/vladimir/Desktop/lab-2/B. Уничтожение графа" "/Users/vladimir/Desktop/lab-2/B. Уничтожение графа/cmake-build-debug" "/Users/vladimir/Desktop/lab-2/B. Уничтожение графа/cmake-build-debug" "/Users/vladimir/Desktop/lab-2/B. Уничтожение графа/cmake-build-debug/CMakeFiles/B___.dir/DependInfo.cmake" --color=$(COLOR)
.PHONY : CMakeFiles/B___.dir/depend

