// bring in basic reset styles only
// 入口文件

import "antd/dist/reset.css";

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
