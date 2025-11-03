// bring in basic reset styles only
// 入口文件

import "antd/dist/antd.css";

import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";

// library的初始化
// 把dom tree挂载到root节点上
// dom tree的托管，react托管
const root = ReactDOM.createRoot(document.getElementById("root"));

// react启动画画，从root开始画，还要告诉reacthow to draw
// react怎么画？通过App组件告诉react怎么画
// 画App组件
// App组件里面可能有其他组件
// 递归的把所有的组件都画出来
// <App> 就是一个component
root.render(<App />);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

// jsx语法
// js + html
// <App></App>
// babel 会把jsx语法转换成js语法

// jxs的结果是一个对象，virtual dom
// 这个对象就是react element

// virtual dom -> real dom
// react 会画很多次这个对象，如果只画一次那就是静态页面了
// 画多次的原因是数据变化了，数据变化了就要重新画
// 重新画的时候会把新的对象和旧的对象进行对比，找出变化的部分
// react 会把这个对象转换成真实的dom
// react 只会把变化的部分更新到真实的dom上

// 前端服务器和后端服务器
// 前端服务器：
// 1. 提供静态资源服务（html, css, js, 图片，字体）
// 2. 提供接口代理服务（把请求转发给后端服务器）
// 后端服务器：
// 1. 处理业务逻辑
// 2. 处理数据存储和读取（数据库）
// 3. 提供接口服务（把数据通过接口返回给前端服务器）
// 前端服务器和后端服务器可以是同一个服务器，也可以是不同的服务器
// 前端服务器和后端服务器可以用不同的技术栈
// 前端服务器常见的技术栈：Node.js + Express, Nginx
// 后端服务器常见的技术栈：Java + Spring Boot, Python + Django, Node.js + Express
// 前端服务器和后端服务器的通信协议：HTTP/HTTPS
// 前端服务器和后端服务器的通信方式：AJAX, Fetch, Axios
// 前端服务器和后端服务器的通信数据格式：JSON, XML
