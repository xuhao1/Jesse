#Jesse 算法概览
##神经网络算法
本项目所涉及的无人机有多层次的神经网络算法控制，高层次分为两个层次

 - 姿态保持 
 - 悬停控制
 - 姿态转移
 
高层次网络在模拟中固定，底层网络用于动力输出，飞行姿态监测，底层网络随着运动不断演进

##高层网络设计
###控制体的神经网络控制
使用神经网络演进的控制算法

>问题1：
>	姿态评估算法设计

>问题2:
>	神经网络个体演进

###姿态评估函数：
	$$Mean[z]$$		均值
	$$\Sigma^2[z]$$	方差
	$$Appra[z]=\frac{1}{Mean^2[z]+\Sigma^2[z]}$$