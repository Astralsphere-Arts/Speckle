@echo off

set name=Speckle
set vendor=Astralsphere Arts
set year=2021-2024
set version=1.0.0

set input_dir=target
set working_dir=target
set resources_dir=resources
set output_dir=release

set nospace=%name: =-%

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
powershell "Compress-Archive '%working_dir%\temp\images\win-msi.image\%name%\*' '%output_dir%\%nospace%-%version%-win-x64.zip'"
del "%working_dir%\temp\images\win-msi.image\%name%\app\%name%.cfg"
powershell "Compress-Archive '%working_dir%\temp\images\win-msi.image\%name%\app\*' '%output_dir%\%nospace%-%version%.zip'"

rd "%working_dir%\app" /s /q
rd "%working_dir%\temp" /s /q

pause
