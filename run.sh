#!/bin/sh

BUILD_DIR="build"

if test ! -d $BUILD_DIR ; then
    ant compile
fi
java -cp "$BUILD_DIR:libs/*" org.jdominion.GUIMain
