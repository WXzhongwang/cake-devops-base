"use strict";(self.webpackChunk=self.webpackChunk||[]).push([[373],{17021:function(pe,R,a){a.r(R),a.d(R,{default:function(){return se}});var G=a(77117),y=a.n(G),J=a(28152),c=a.n(J),v=a(93236),M=a(30189),f=a(75427),n=a(67801),V=a(30301),$=a(93034),Q=a(95020),C=a(31756),p=a(53330),W=a(23817),X=a(56425),Y=a(51593),k=a(13448),P=a.n(k),w=a(74815),q=a.n(w),_=a(67855),ee=a.n(_),ae=a(98984),re=a(20401),ne=a(38444),e=a(62086),S=f.Z.Option,te=function(u){var d=u.initialValues,T=u.onSave,L=u.onCancel,b=n.Z.useForm(),N=c()(b,1),h=N[0],j=v.useState([]),B=c()(j,2),F=B[0],D=B[1];v.useEffect(function(){h.setFieldsValue(d)},[h,d]);var Z=function(m){var o=ee()(m.fileList);o=o.slice(-1),o=o.map(function(l){return l.response&&(l.url=l.response.url),l}),D(o)},E=function(){var g=q()(P()().mark(function m(){var o,l,x;return P()().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.prev=0,r.next=3,h.validateFields();case 3:if(o=r.sent,F.length!==0){r.next=7;break}return ae.ZP.error("\u8BF7\u9009\u62E9\u8981\u4E0A\u4F20\u7684\u6587\u4EF6"),r.abrupt("return");case 7:l=F[0],x=new FileReader,x.readAsDataURL(l.originFileObj),x.onloadend=function(){var K=x.result,O=K.split(",")[1];T(o,O)},r.next=16;break;case 13:r.prev=13,r.t0=r.catch(0),console.error("\u8868\u5355\u9A8C\u8BC1\u5931\u8D25:",r.t0);case 16:case"end":return r.stop()}},m,null,[[0,13]])}));return function(){return g.apply(this,arguments)}}();return(0,e.jsxs)(n.Z,{form:h,layout:"vertical",children:[(0,e.jsx)(n.Z.Item,{name:"displayName",label:"\u663E\u793A\u540D\u79F0",rules:[{required:!0,message:"\u8BF7\u8F93\u5165\u663E\u793A\u540D\u79F0"}],children:(0,e.jsx)(C.Z,{placeholder:"\u8BF7\u8F93\u5165\u663E\u793A\u540D\u79F0"})}),(0,e.jsx)(n.Z.Item,{name:"accountType",label:"\u8D26\u6237\u7C7B\u578B",rules:[{required:!0,message:"\u8BF7\u9009\u62E9\u8D26\u6237\u7C7B\u578B"}],children:(0,e.jsxs)(f.Z,{placeholder:"\u8BF7\u9009\u62E9\u8D26\u6237\u7C7B\u578B",children:[(0,e.jsx)(S,{value:1,children:"\u7BA1\u7406\u5458"}),(0,e.jsx)(S,{value:2,children:"\u666E\u901A\u8D26\u6237"})]})}),(0,e.jsx)(n.Z.Item,{label:"\u534F\u8BAE",name:"protocol",initialValue:"SSH",rules:[{required:!0,message:"\u8BF7\u9009\u62E9\u534F\u8BAE"}],children:(0,e.jsx)(f.Z,{placeholder:"\u8BF7\u9009\u62E9\u534F\u8BAE",disabled:!0,children:(0,e.jsx)(S,{value:"SSH",children:"SSH"})})}),(0,e.jsx)(n.Z.Item,{name:"active",label:"\u6D3B\u8DC3\u72B6\u6001",rules:[{required:!0,message:"\u8BF7\u9009\u62E9\u6D3B\u8DC3\u72B6\u6001"}],children:(0,e.jsxs)(f.Z,{placeholder:"\u8BF7\u9009\u62E9\u6D3B\u8DC3\u72B6\u6001",children:[(0,e.jsx)(S,{value:"1",children:"\u6D3B\u8DC3"}),(0,e.jsx)(S,{value:"0",children:"\u4E0D\u6D3B\u8DC3"})]})}),(0,e.jsx)(n.Z.Item,{name:"credentialContent",label:"\u51ED\u636E\u5185\u5BB9",rules:[{required:!0,message:"\u8BF7\u8F93\u5165\u51ED\u636E\u5185\u5BB9"}],children:(0,e.jsx)(C.Z.TextArea,{placeholder:"\u8BF7\u8F93\u5165\u51ED\u636E\u5185\u5BB9"})}),(0,e.jsx)(n.Z.Item,{name:"passphrase",label:"\u5BC6\u7801\u77ED\u8BED",rules:[{required:!0,message:"\u8BF7\u8F93\u5165\u5BC6\u7801\u77ED\u8BED"}],children:(0,e.jsx)(C.Z.Password,{placeholder:"\u8BF7\u8F93\u5165\u5BC6\u7801\u77ED\u8BED"})}),(0,e.jsx)(n.Z.Item,{name:"file",label:"\u4E0A\u4F20\u6587\u4EF6",rules:[{required:!0,message:"\u8BF7\u9009\u62E9\u8981\u4E0A\u4F20\u7684\u6587\u4EF6"}],children:(0,e.jsx)(re.Z,{fileList:F,onChange:Z,beforeUpload:function(){return!1},children:(0,e.jsx)(p.Z,{icon:(0,e.jsx)(ne.Z,{}),children:"\u9009\u62E9\u6587\u4EF6"})})}),(0,e.jsxs)(n.Z.Item,{children:[(0,e.jsx)(p.Z,{type:"primary",onClick:E,children:d?"\u66F4\u65B0":"\u4FDD\u5B58"}),(0,e.jsx)(p.Z,{onClick:L,style:{marginLeft:8},children:"\u53D6\u6D88"})]})]})},ue=te,U=f.Z.Option,le=function(u){var d=u.dispatch,T=u.serverKeys,L=u.serverKeyTotal,b=u.hosts,N=(0,v.useState)({pageNo:1,pageSize:10}),h=c()(N,2),j=h[0],B=h[1],F=(0,v.useState)({displayName:"",active:""}),D=c()(F,2),Z=D[0],E=D[1],g=(0,v.useState)(!1),m=c()(g,2),o=m[0],l=m[1],x=(0,v.useState)(void 0),z=c()(x,2),r=z[0],K=z[1],O=n.Z.useForm(),ie=c()(O,1),oe=ie[0],de=function(){l(!0)},H=function(){l(!1),oe.resetFields(),K(void 0)},ce=function(t,s){console.log("Form values:",t);var A=y()(y()({},t),{},{fileBase64:s});d(r?{type:"host/updateServerKey",payload:y()(y()({},A),{},{id:r.id})}:{type:"host/createServerKey",payload:A}),l(!1)},ve=function(t){d({type:"host/deleteServerKey",payload:{serverKeyId:t}})};(0,v.useEffect)(function(){he()},[j,Z]);var he=function(){d({type:"host/queryServerKeys",payload:y()(y()({},j),Z)})},me=[{title:"\u663E\u793A\u540D\u79F0",dataIndex:"displayName",key:"displayName"},{title:"\u8D26\u6237\u7C7B\u578B",dataIndex:"accountType",key:"accountType",render:function(t,s){return(0,e.jsx)(V.Z,{color:s.accountType===1?"red":"blue",children:s.accountType===1?"\u7BA1\u7406\u5458":"\u666E\u901A\u8D26\u6237"})}},{title:"\u534F\u8BAE",dataIndex:"protocol",key:"protocol"},{title:"\u6D3B\u8DC3\u72B6\u6001",dataIndex:"active",key:"active",render:function(t,s){return(0,e.jsx)(V.Z,{color:s.active==="1"?"green":"gray",children:s.active==="1"?"\u6D3B\u8DC3":"\u4E0D\u6D3B\u8DC3"})}},{title:"\u64CD\u4F5C",key:"action",render:function(t,s){return(0,e.jsxs)($.Z,{size:"middle",children:[(0,e.jsx)("a",{onClick:function(){return fe(s)},children:"\u7F16\u8F91"}),(0,e.jsx)("a",{onClick:function(){return ve(s.id)},children:"\u5220\u9664"})]})}}],ye=function(t,s){B({pageNo:t,pageSize:s||10})},fe=function(t){K(t),l(!0)};return(0,e.jsxs)(M._z,{title:"\u4E3B\u673A\u79D8\u94A5\u7BA1\u7406",children:[(0,e.jsx)(Q.Z,{children:(0,e.jsxs)($.Z,{size:"middle",direction:"vertical",style:{width:"100%"},children:[(0,e.jsxs)(n.Z,{layout:"inline",onFinish:function(t){console.log(t),E(t)},children:[(0,e.jsx)(n.Z.Item,{name:"displayName",label:"\u7528\u6237\u540D",children:(0,e.jsx)(C.Z,{placeholder:"\u8BF7\u8F93\u5165\u7528\u6237\u540D"})}),(0,e.jsx)(n.Z.Item,{name:"active",label:"\u6D3B\u8DC3\u72B6\u6001",children:(0,e.jsxs)(f.Z,{placeholder:"\u8BF7\u9009\u62E9\u6D3B\u8DC3\u72B6\u6001",allowClear:!0,children:[(0,e.jsx)(U,{value:"1",children:"\u6D3B \u8DC3"}),(0,e.jsx)(U,{value:"0",children:"\u4E0D\u6D3B\u8DC3"})]})}),(0,e.jsxs)(n.Z.Item,{children:[(0,e.jsx)(p.Z,{type:"primary",htmlType:"submit",style:{marginRight:8},children:"\u67E5\u8BE2"}),(0,e.jsx)(p.Z,{onClick:function(){E({displayName:"",active:""})},children:"\u91CD\u7F6E"})]})]}),(0,e.jsx)(p.Z,{type:"primary",onClick:de,children:"\u65B0\u589E\u79D8\u94A5"}),(0,e.jsx)(W.Z,{columns:me,dataSource:T,rowKey:"id",pagination:{total:L,current:j.pageNo,pageSize:j.pageSize,onChange:ye}})]})}),(0,e.jsx)(X.Z,{title:r?"\u7F16\u8F91\u79D8\u94A5":"\u65B0\u589E\u79D8\u94A5",width:400,open:o,onClose:H,destroyOnClose:!0,children:(0,e.jsx)(ue,{initialValues:r,onSave:ce,onCancel:H})})]})},se=(0,Y.connect)(function(I){var u=I.host;return{hosts:u.hosts,serverKeys:u.serverKeys,serverKeyTotal:u.serverKeyTotal}})(le)}}]);
