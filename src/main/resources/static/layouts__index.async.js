(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[1],{aArQ:function(e,t,a){"use strict";a.r(t);var n,l,s=a("q1tI"),o=a.n(s),r=(a("jDDT"),a("GGqY")),c=a("tiWs"),i=a("374E"),p=a("a5/R"),d=a("nsn4"),u=a("9a8N"),m=a("MuoO"),h=a("3a4m"),b=a.n(h),g=a("5v02"),E=a("Tgqd"),v=(n=Object(m["connect"])(e=>{var t=e.globalModel;return{globalModel:t}}),n(l=class extends s["Component"]{constructor(){super(),this.state={isOpen:!1}}render(){var e=()=>{this.setState({isOpen:!this.state.isOpen})},t=this.props.globalModel,a=t.isMenu,n=()=>{this.props.dispatch({type:"globalModel/updateState",payload:{isMenu:!a}})},l=()=>{localStorage.removeItem(g["g"]),this.props.dispatch({type:"globalModel/updateState",payload:{currentUser:"",isStaff:!1,isTeacher:!1,isStudent:!1,isDirector:!1}}),b.a.push("/auth/login")};return o.a.createElement("div",null,o.a.createElement(c["a"],{color:"success",light:!0,expand:"md"},o.a.createElement(i["a"],{className:window.location.pathname.length<2?"d-none":"d-block"},o.a.createElement(E["a"],{onClick:n})),o.a.createElement(p["a"],{onClick:e}),o.a.createElement(d["a"],{isOpen:this.state.isOpen,navbar:!0},o.a.createElement(u["a"],{className:"mr-auto",navbar:!0}),o.a.createElement("button",{className:"btn btn-dark",onClick:l},"logout"))))}})||l),w=v;function M(e){return o.a.createElement("div",null,o.a.createElement(r["a"],null),o.a.createElement(w,null),e.children)}t["default"]=M}}]);