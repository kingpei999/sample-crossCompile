#!/bin/bash
NDK_ROOT=/home/kingpei/android-sdk/ndk-bundle
PREBUILT=$NDK_ROOT/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64
PLATFORM=$NDK_ROOT/platforms/android-28/arch-arm
SYSROOT=$NDK_ROOT/sysroot
export PATH=$PATH:$PREBULT/bin:$SYSROOT/usr/include:

export LDFLAGS="-march=armv7-a -L$PLATFORM/usr/lib -L$PREBUILT/arm-linux-androideabi/lib -L$SYSROOT/usr/lib"
export CFLAGS="-march=armv7-a -I$SYSROOT/usr/include -mfloat-abi=softfp -mfpu=vfp -ffast-math -O2"

export CPPFLAGS="$CFLAGS"
export CXXFLAGS="$CFLAGS"
export LDFLAGS="$LDFLAGS"
export CFLAGS="$CFLAGS"

export AS=$PREBUILT/bin/arm-linux-androideabi-as
export LD=$PREBUILT/bin/arm-linux-androideabi-ld
export CXX="$PREBUILT/bin/arm-linux-androideabi-g++ --sysroot=${PLATFORM}"
export CC="$PREBUILT/bin/arm-linux-androideabi-gcc --sysroot=${PLATFORM} -march=armv7-a"
export NM=$PREBUILT/bin/arm-linux-androideabi-nm
export STRIP=$PREBUILT/bin/arm-linux-androideabi-strip
export RANLIB=$PREBUILT/bin/arm-linux-androideabi-ranlib
export AR=$PREBUILT/bin/arm-linux-androideabi-ar

./configure --host=arm-linux \
--disable-shared \
--disable-frontend \
--enable-static \
--prefix=/home/kingpei/music-video/lame-3.99.5/armv7a

make clean
make -j8
make install

