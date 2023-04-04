cmake_minimum_required(VERSION 3.21)
project(bfs)

set(CMAKE_CXX_STANDARD 11)

SET(CMAKE_BUILD_TYPE RELEASE)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -O0 -fopenmp")

add_executable(bfs bfs.cpp)
