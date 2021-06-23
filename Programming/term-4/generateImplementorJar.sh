#!/bin/bash
advanced=../../java-advanced-2021
mkdir -p info/kgeorgiy/java/advanced/implementor
jar xf $advanced/artifacts/info.kgeorgiy.java.advanced.implementor.jar \
  info/kgeorgiy/java/advanced/implementor/{Impler.class,JarImpler.class,ImplerException.class}
javac info/kgeorgiy/ja/popov/implementor/Implementor.java
jar cevf info.kgeorgiy.ja.popov.implementor.Implementor \
 info.kgeorgiy.ja.popov.implementor.Implementor.jar \
 info/kgeorgiy/ja/popov/implementor/Implementor.class \
 info/kgeorgiy/java/advanced/implementor/{Impler.class,JarImpler.class,ImplerException.class}
