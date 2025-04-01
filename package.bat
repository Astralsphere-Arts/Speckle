@echo off

set name=Speckle
set vendor=Astralsphere Arts
set year=2021-2025
set version=1.5.0

set input_dir=target
set working_dir=target
set resources_dir=resources
set output_dir=release
set seven_zip="C:\Program Files\7-Zip\7z.exe"

set nospace=%name: =-%

call mvn clean verify

if exist "%working_dir%\app" rd "%working_dir%\app" /s /q
if exist "%working_dir%\temp" rd "%working_dir%\temp" /s /q
if exist "%output_dir%\%nospace%-%version%*.*" del "%output_dir%\%nospace%-%version%*.*"

md "%working_dir%\app\lib"
md "%working_dir%\temp"

copy "LICENSE" "%working_dir%\app"
copy "%input_dir%\*.jar" "%working_dir%\app"
copy "%input_dir%\lib" "%working_dir%\app\lib"

jpackage^
  --input "%working_dir%/app"^
  --dest "%output_dir%"^
  --resource-dir "%resources_dir%"^
  --temp "%working_dir%/temp"^
  --name "%name%"^
  --vendor "%vendor%"^
  --main-jar "%name%.jar"^
  --app-version %version%^
  --copyright "Copyright (c) %year% %vendor%"^
  --icon "%resources_dir%/app.ico"^
  --add-modules java.base,java.desktop,java.sql^
  --jlink-options "--no-header-files --no-man-pages --strip-debug --strip-native-commands --compress=2"^
  --type exe^
  --license-file "%resources_dir%/LICENSE.rtf"^
  --win-dir-chooser^
  --win-menu^
  --win-menu-group "%vendor%"^
  --win-shortcut^
  --win-shortcut-prompt

ren "%output_dir%\%name%-%version%.exe" "%nospace%-%version%-win-x64.exe"
copy "%working_dir%\temp\images\win-exe.image\%name%-%version%.msi" "%output_dir%\%nospace%-%version%-win-x64.msi"
del "%working_dir%\temp\images\win-msi.image\%name%\app\.jpackage.xml"
%seven_zip% a "%output_dir%\%nospace%-%version%-win-x64.zip" ".\%working_dir%\temp\images\win-msi.image\%name%\*"
del "%working_dir%\temp\images\win-msi.image\%name%\app\%name%.cfg"
%seven_zip% a "%output_dir%\%nospace%-%version%.zip" ".\%working_dir%\temp\images\win-msi.image\%name%\app\*"

rd "%working_dir%\app" /s /q
rd "%working_dir%\temp" /s /q

pause
