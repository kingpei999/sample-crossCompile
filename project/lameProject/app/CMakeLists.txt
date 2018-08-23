  cmake_minimum_required(VERSION 3.6)
add_library(mp3encoder
    SHARED
        src/main/cpp/mp3encoderWorker.cpp
    src/main/cpp/mp3encoder.cpp)

add_library(mp3lame
STATIC
IMPORTED)

set_target_properties(mp3lame
PROPERTIES IMPORTED_LOCATION
${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libmp3lame.a)

include_directories( src/main/cpp/include )

find_library(log-lib
              log )

target_link_libraries(mp3encoder
                       ${log-lib}
                       mp3lame)