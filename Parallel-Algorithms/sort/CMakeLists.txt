cmake_minimum_required(VERSION 3.21)
project(sort)

set(CMAKE_CXX_STANDARD 14)

target_include_directories(${PROJECT_NAME} PRIVATE ${CILK_INCLUDE_DIR})
target_link_libraries(${PROJECT_NAME} ${CILK_INCLUDE_DIR})

include_directories(${CILK_INCLUDE_DIR})
link_libraries(${CILK_INCLUDE_DIR})

link_directories(${CILK_INCLUDE_DIR})
include_directories(../cilk)
add_subdirectory(../cilk)

add_executable(sort qsort_seq.cpp qsort_par.cpp)
