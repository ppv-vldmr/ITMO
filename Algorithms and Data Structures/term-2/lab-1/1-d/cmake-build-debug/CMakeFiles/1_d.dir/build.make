# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.15

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "D:\CLion 2019.3.3\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "D:\CLion 2019.3.3\bin\cmake\win\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\Users\vovan\Desktop\term-2\lab-1\1-d

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\Users\vovan\Desktop\term-2\lab-1\1-d\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/1_d.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/1_d.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/1_d.dir/flags.make

CMakeFiles/1_d.dir/main.cpp.obj: CMakeFiles/1_d.dir/flags.make
CMakeFiles/1_d.dir/main.cpp.obj: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\vovan\Desktop\term-2\lab-1\1-d\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/1_d.dir/main.cpp.obj"
	C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\G__~1.EXE  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\1_d.dir\main.cpp.obj -c C:\Users\vovan\Desktop\term-2\lab-1\1-d\main.cpp

CMakeFiles/1_d.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/1_d.dir/main.cpp.i"
	C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\Users\vovan\Desktop\term-2\lab-1\1-d\main.cpp > CMakeFiles\1_d.dir\main.cpp.i

CMakeFiles/1_d.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/1_d.dir/main.cpp.s"
	C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\Users\vovan\Desktop\term-2\lab-1\1-d\main.cpp -o CMakeFiles\1_d.dir\main.cpp.s

# Object files for target 1_d
1_d_OBJECTS = \
"CMakeFiles/1_d.dir/main.cpp.obj"

# External object files for target 1_d
1_d_EXTERNAL_OBJECTS =

1_d.exe: CMakeFiles/1_d.dir/main.cpp.obj
1_d.exe: CMakeFiles/1_d.dir/build.make
1_d.exe: CMakeFiles/1_d.dir/linklibs.rsp
1_d.exe: CMakeFiles/1_d.dir/objects1.rsp
1_d.exe: CMakeFiles/1_d.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\vovan\Desktop\term-2\lab-1\1-d\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable 1_d.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\1_d.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/1_d.dir/build: 1_d.exe

.PHONY : CMakeFiles/1_d.dir/build

CMakeFiles/1_d.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\1_d.dir\cmake_clean.cmake
.PHONY : CMakeFiles/1_d.dir/clean

CMakeFiles/1_d.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\vovan\Desktop\term-2\lab-1\1-d C:\Users\vovan\Desktop\term-2\lab-1\1-d C:\Users\vovan\Desktop\term-2\lab-1\1-d\cmake-build-debug C:\Users\vovan\Desktop\term-2\lab-1\1-d\cmake-build-debug C:\Users\vovan\Desktop\term-2\lab-1\1-d\cmake-build-debug\CMakeFiles\1_d.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/1_d.dir/depend

