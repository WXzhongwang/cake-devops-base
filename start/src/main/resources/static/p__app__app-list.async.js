"use strict";(self.webpackChunk=self.webpackChunk||[]).push([[551],{75452:function(_,C,a){a.r(C),a.d(C,{default:function(){return M}});var z=a(77117),y=a.n(z),P=a(28152),d=a.n(P),h=a(93236),T=a(30189),p=a(15762),t=a(82087),D=a(93034),V=a(95020),l=a(1042),m=a(12203),k=a(90874),j=a(9069),w=a(80442),e=a(62086),s=p.Z.Option,$=function(n){var A=n.dispatch,F=n.open,x=n.onClose,v=t.Z.useForm(),i=d()(v,1),E=i[0],Z=function(c){console.log("\u521B\u5EFA\u5E94\u7528:",c),A({type:"app/createApp",payload:c}),x()};return(0,e.jsx)(w.Z,{title:"\u6DFB\u52A0\u5E94\u7528",width:400,onClose:x,open:F,destroyOnClose:!0,children:(0,e.jsxs)(t.Z,{form:E,onFinish:Z,layout:"vertical",children:[(0,e.jsx)(t.Z.Item,{name:"appName",label:"\u5E94\u7528\u540D\u79F0",rules:[{required:!0,message:"\u8BF7\u8F93\u5165\u5E94\u7528\u540D\u79F0"}],children:(0,e.jsx)(l.Z,{placeholder:"\u8BF7\u8F93\u5165\u5E94\u7528\u540D\u79F0"})}),(0,e.jsx)(t.Z.Item,{name:"repo",label:"\u4ED3\u5E93\u5730\u5740",rules:[{required:!0,message:"\u8BF7\u8F93\u5165\u4ED3\u5E93\u5730\u5740"}],children:(0,e.jsx)(l.Z,{placeholder:"\u8BF7\u8F93\u5165\u4ED3\u5E93\u5730\u5740"})}),(0,e.jsx)(t.Z.Item,{name:"defaultBranch",label:"\u9ED8\u8BA4\u5206\u652F",rules:[{required:!0,message:"\u8BF7\u8F93\u5165\u9ED8\u8BA4\u5206\u652F"}],children:(0,e.jsx)(l.Z,{placeholder:"\u8BF7\u8F93\u5165\u9ED8\u8BA4\u5206\u652F"})}),(0,e.jsx)(t.Z.Item,{name:"developMode",label:"\u5F00\u53D1\u6A21\u5F0F",rules:[{required:!0,message:"\u8BF7\u9009\u62E9\u5F00\u53D1\u6A21\u5F0F"}],children:(0,e.jsxs)(p.Z,{placeholder:"\u8BF7\u9009\u62E9\u5F00\u53D1\u6A21\u5F0F",children:[(0,e.jsx)(s,{value:"Freedom",children:"\u81EA\u7531\u6A21\u5F0F"}),(0,e.jsx)(s,{value:"GitFlow",children:"\u6807\u51C6\u6A21\u5F0F"}),(0,e.jsx)(s,{value:"Branch",children:"\u5206\u652F\u6A21\u5F0F"})]})}),(0,e.jsx)(t.Z.Item,{name:"language",label:"\u5F00\u53D1\u8BED\u8A00",rules:[{required:!0,message:"\u8BF7\u9009\u62E9\u5F00\u53D1\u8BED\u8A00"}],children:(0,e.jsxs)(p.Z,{placeholder:"\u8BF7\u9009\u62E9\u5F00\u53D1\u8BED\u8A00",children:[(0,e.jsx)(s,{value:"JAVA",children:"Java"}),(0,e.jsx)(s,{value:"PYTHON",children:"Python"}),(0,e.jsx)(s,{value:"GO",children:"GO"})]})}),(0,e.jsx)(t.Z.Item,{name:"healthCheck",label:"\u5065\u5EB7\u68C0\u67E5\u5730\u5740",rules:[{required:!0,message:"\u8BF7\u8F93\u5165\u5065\u5EB7\u68C0\u67E5\u5730\u5740"}],children:(0,e.jsx)(l.Z,{placeholder:"\u8BF7\u8F93\u5165\u5065\u5EB7\u68C0\u67E5\u5730\u5740"})}),(0,e.jsx)(t.Z.Item,{children:(0,e.jsx)(m.Z,{type:"primary",htmlType:"submit",children:"\u63D0\u4EA4"})})]})})},G=(0,j.connect)()($),B=p.Z.Option,J=function(n){var A=n.dispatch,F=n.appList,x=(0,h.useState)({pageNo:1,pageSize:10}),v=d()(x,2),i=v[0],E=v[1],Z=(0,h.useState)({appName:"",department:"",language:""}),f=d()(Z,2),c=f[0],I=f[1],b=(0,h.useState)(!1),S=d()(b,2),H=S[0],N=S[1],K=function(){N(!0)},Y=function(){N(!1)},Q=t.Z.useForm(),R=d()(Q,1),L=R[0];(0,h.useEffect)(function(){U()},[i,c]);var U=function(){A({type:"app/getAppList",payload:y()(y()({},i),c)})},W=[{title:"\u5E94\u7528\u540D\u79F0",dataIndex:"appName",key:"appName",render:function(u,o){return(0,e.jsx)("a",{onClick:function(){return O(o)},children:o.appName})}},{title:"\u4ED3\u5E93",dataIndex:"repo",key:"repo",width:200},{title:"\u9ED8\u8BA4\u5206\u652F",dataIndex:"defaultBranch",key:"defaultBranch"},{title:"\u90E8\u95E8",dataIndex:"department",key:"department"},{title:"\u5F00\u53D1\u8BED\u8A00",dataIndex:"language",key:"language"},{title:"\u63CF\u8FF0",dataIndex:"description",key:"description"},{title:"\u5F00\u53D1\u6A21\u5F0F",dataIndex:"developMode",key:"developMode"},{title:"\u72B6\u6001",dataIndex:"status",key:"status"},{title:"\u64CD\u4F5C",key:"action",render:function(u,o){return(0,e.jsx)(D.Z,{size:"middle",children:(0,e.jsx)("a",{onClick:function(){return O(o)},children:"\u67E5\u770B"})})}}],X=function(u,o){E({pageNo:u,pageSize:o||10})},O=function(u){console.log("\u67E5\u770B\u5E94\u7528\u8BE6\u60C5",u),j.history.push("/app-detail/".concat(u.id))};return(0,e.jsx)(T._z,{children:(0,e.jsx)(V.Z,{children:(0,e.jsxs)(D.Z,{size:"middle",direction:"vertical",style:{width:"100%"},children:[(0,e.jsxs)(t.Z,{form:L,layout:"inline",onFinish:function(u){console.log(u),I(u)},children:[(0,e.jsx)(t.Z.Item,{name:"appName",label:"\u5E94\u7528\u540D\u79F0",children:(0,e.jsx)(l.Z,{placeholder:"\u8BF7\u8F93\u5165\u5E94\u7528\u540D\u79F0"})}),(0,e.jsx)(t.Z.Item,{name:"department",label:"\u90E8\u95E8",children:(0,e.jsx)(l.Z,{placeholder:"\u8BF7\u8F93\u5165\u90E8\u95E8"})}),(0,e.jsx)(t.Z.Item,{name:"language",label:"\u5F00\u53D1\u8BED\u8A00",children:(0,e.jsxs)(p.Z,{placeholder:"\u8BF7\u9009\u62E9\u5F00\u53D1\u8BED\u8A00",children:[(0,e.jsx)(B,{value:"java",children:"Java"}),(0,e.jsx)(B,{value:"python",children:"Python"})]})}),(0,e.jsxs)(t.Z.Item,{children:[(0,e.jsx)(m.Z,{type:"primary",htmlType:"submit",children:"\u67E5\u8BE2"}),(0,e.jsx)(m.Z,{onClick:function(){L.resetFields(),I({appName:"",department:"",language:""})},children:"\u91CD\u7F6E"})]})]}),(0,e.jsx)(m.Z,{type:"primary",onClick:K,children:"\u521B\u5EFA\u5E94\u7528"}),(0,e.jsx)(G,{open:H,onClose:Y}),(0,e.jsx)(k.Z,{columns:W,dataSource:F.list,rowKey:"id",pagination:{total:F.total,current:i.pageNo,pageSize:i.pageSize,onChange:X}})]})})})},M=(0,j.connect)(function(g){var n=g.app;return{appList:n.appList}})(J)}}]);
