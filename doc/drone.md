#Drone
##技术准备
###硬件
通信:

- 串行口（uart）
- IIC
- 蓝牙，无线网络：Socket(TCP/IP or UDP),HTTP
- 协议 Mavlink
- PMW

硬件:

- 超声波测距
- 激光雷达
- 陀螺仪，角加速度，磁场，气压计
- GPS 通信
- 摄像头（采集）
- arm板的使用
- 舵机

软件：

 - $$$\mu cos$$$
 - arm linux：
 	- linux使用 bash (freeshell.ustc.edu.cn) ,ssh
 	- linux 编译 (gcc javac ,gdb)
 	- 自己装个Ubuntu
 - 视频压缩与传输
 - Apache2（HTTP服务器）
 - 通信协议(java) << Think in Java>>
 
飞机：
 
 - 了解基本的空气动力学知识一点点：
 	
 	- 螺旋桨
 	- 升力
 	- 三轴控制
 
 - 了解自动控制：
 
  	- PIDs
 
 平台：
 
- 对apm以及px4的目录结构有一定了解

#无人机实现方案

>四轴无人机是一种在航模领域早已充分开发，在民用军用领域应用广泛的无人机平台。最早的直升机就是四轴飞行器

##技术特点综述：
四轴飞行器有控制简单，机械结构简单等特点，而且对起降场地要求少。这几年随着小型陀螺仪的普及化，四轴飞行器大大普及。不像传统有副翼直升机的复杂的机械结构，一个普通人很快可以组织起来。

##飞控软件

流行的四轴飞行器控制软件很多，开源，功能多样的主要是PX4和APM，都是基于PIDs控制，在实现平台不同。

##项目方案
我们对于整个飞控系统设计了两种方案,使用单片机平台与安卓操作系统结合的方案:

我们使用神经网络进行多层的控制。底层的运动传感器检测以及运动积分全部交由单片机完成。使用安卓系统和地面站进行网络通信，这就要求了整个飞行器在控制可以依靠飞控自主飞行。

##硬件
- 飞行器：四轴飞行器一架
- 硬件控制系统：Arduino单片机
- 飞行算法：四核Arm开发板

##目标

 - 1 使用异步的网络通信在GPS轨迹内航拍
 - 2 使用自主的模式识别算法进行飞行控制
 - 3 自动飞行的同时进行周边环境的三维重构

##飞行控制：
    能做到限定时空下的限定姿态飞行。

##飞行器重设计
 - 大小：全长一米左右

##机器视觉算法
使用机器视觉算法作为飞行的基本依据，使用光流辅助飞行。

##三维重构
将飞行所得数据进行三维重构。

使用扫描线进行辅助分析

##多机协作
利用GPS的差分定位，在室外使用多架飞机辅助进行三维探测，在室内使用多架飞机进行探测。以两架为宜