##  uav-homework ##

uav-homework is a homework for ThoughtWorks .

###　Runtime requirement ###

* java8

* maven

### How to test,exec or build ? ###

* you can run the uav_test.bat or use next shell to test the unit case.
   ```shell
   mvn test
   ```
   next is the example:
  ![Aaron Swartz](https://raw.githubusercontent.com/FanHuaRan/display_pictures/master/uav_homework/test.PNG)

* you can run the uav_exec.bat or use next shell to run command app.
   ```shell
   mvn exec:java -Dexec.mainClass="com.fhr.uavhomework.bootstrap.App" -Dexec.classpathScope=runtime
   ```
    next is the example:
    ![Aaron Swartz](https://raw.githubusercontent.com/FanHuaRan/display_pictures/master/uav_homework/exec.PNG)
    
* you can run the uav_build.bat or use next shell to build the jar.
   ```shell
   mvn package
   ```
  
### design ideas ###
本工程使用maven构建，在具体的设计实现当中，根据实际情况使用面向对象设计原则和设计模式，力求简单高效但不丧失扩展性。
本工程核心是版本化和不可变：每个无人机指令都对应一个场景，这个场景下有各个无人机，指令输入导致一个场景到另外一个场景的变迁。
模块如下：
    ![Aaron Swartz](https://raw.githubusercontent.com/FanHuaRan/display_pictures/master/uav_homework/component.PNG
)
### licence ###

* Apache License

## About author
```javascript
  var author = {
    name  : "范华燃",
    English name : "Evan"
    email : "ranrandemo@gmail.com"
  }
```


