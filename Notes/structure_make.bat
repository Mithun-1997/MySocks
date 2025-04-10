@echo off
setlocal enabledelayedexpansion

:: Define base directory (src/main/java and src/main/resources)
set BASE_DIR=%CD%\src\main

:: Define folder structure
set FOLDERS=^
    %BASE_DIR%\java\com\devops\server\filter ^
    %BASE_DIR%\java\com\devops\server\gateway ^
    %BASE_DIR%\java\com\devops\server\interceptor ^
    %BASE_DIR%\java\com\devops\service\impl ^
    %BASE_DIR%\java\com\devops\service\controller ^
    %BASE_DIR%\java\com\devops\service\dto ^
    %BASE_DIR%\java\com\devops\database\entity ^
    %BASE_DIR%\java\com\devops\database\repository ^
    %BASE_DIR%\java\com\devops\config ^
    %BASE_DIR%\resources\config ^
    %BASE_DIR%\resources\static ^
    %BASE_DIR%\resources\templates ^
    %BASE_DIR%\resources\database_script

:: Create folders
for %%F in (%FOLDERS%) do (
    mkdir "%%F" 2>nul
)

:: Define files with their respective folders
set FILES=^
    %BASE_DIR%\java\com\devops\server\filter\JwtAuthenticationFilter.java ^
    %BASE_DIR%\java\com\devops\server\filter\CorsFilter.java ^
    %BASE_DIR%\java\com\devops\server\interceptor\LoggingInterceptor.java ^
    %BASE_DIR%\java\com\devops\server\gateway\GatewayConfig.java ^
    %BASE_DIR%\java\com\devops\server\ServerConfig.java ^
    %BASE_DIR%\java\com\devops\service\impl\AuthServiceImpl.java ^
    %BASE_DIR%\java\com\devops\service\impl\UserServiceImpl.java ^
    %BASE_DIR%\java\com\devops\service\impl\AdminServiceImpl.java ^
    %BASE_DIR%\java\com\devops\service\controller\AuthController.java ^
    %BASE_DIR%\java\com\devops\service\controller\UserController.java ^
    %BASE_DIR%\java\com\devops\service\controller\AdminController.java ^
    %BASE_DIR%\java\com\devops\service\dto\UserRequestDto.java ^
    %BASE_DIR%\java\com\devops\service\dto\AdminRequestDto.java ^
    %BASE_DIR%\java\com\devops\database\entity\User.java ^
    %BASE_DIR%\java\com\devops\database\entity\Admin.java ^
    %BASE_DIR%\java\com\devops\database\repository\UserRepository.java ^
    %BASE_DIR%\java\com\devops\database\repository\AdminRepository.java ^
    %BASE_DIR%\java\com\devops\config\SecurityConfig.java ^
    %BASE_DIR%\resources\config\application.properties ^
    %BASE_DIR%\resources\config\environment.properties ^
    %BASE_DIR%\resources\database_script\schema.sql ^
    %BASE_DIR%\resources\database_script\data.sql

:: Create files
for %%F in (%FILES%) do (
    if not exist "%%F" (
        type nul > "%%F"
    )
)

echo Folder structure and empty files created successfully!
pause
