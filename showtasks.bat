call runcrud
if "%ERRORLEVEL%" == "0" goto rename
echo.
echo GRADLEW BUILD has errors – breaking work
goto fail

:rename
start firefox [url = http://localhost:8080/crud/v1/tasks]http://localhost:8080/crud/v1/tasks[/url]
goto end

:fail
echo.
echo ERROR!!

:end
echo.
echo Work is finished