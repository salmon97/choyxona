(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[7],{"/t9W":function(e,t,a){"use strict";var l,n,o=a("q1tI"),s=a.n(o),r=a("K7vz"),c=a("ujA5"),i=a("6YkS"),m=a("33Fu"),d=a("MuoO"),u=a("Iu7H"),p=a("X13+"),E=a("ma3e"),h=a("NIcq"),j=(l=Object(d["connect"])(e=>{var t=e.globalModel;return{globalModel:t}}),l(n=class extends o["Component"]{render(){var e=this.props.globalModel,t=e.currentUser,a=e.isMenu,l=(e.isStaff,e.isDirector),n=()=>{this.props.dispatch({type:"globalModel/updateState",payload:{isMenu:!a}})};return s.a.createElement("div",{className:a?" katalog-sidebar w18 ":"katalog-sidebar w0 "},s.a.createElement("div",{className:a?"menuSidebar w16 ":"menuSidebar w0 "},s.a.createElement("div",{className:"userStatus text-center text-white"},s.a.createElement("h5",{className:""},t.firstName),s.a.createElement("div",{className:"m-auto w-25"},s.a.createElement("h6",null,"Online"))),s.a.createElement(r["a"],{className:""},s.a.createElement(c["a"],{className:""},s.a.createElement(i["Link"],{to:"/today",className:"/today"===window.location.pathname?"active-catalog":"nav-link",onClick:window.screen.width<850?n:""},s.a.createElement(m["a"],{className:"list-group-item-icon"})," Bugun")),s.a.createElement(c["a"],null,s.a.createElement(i["Link"],{to:"/month",className:"/month"===window.location.pathname?"active-catalog":"nav-link",onClick:window.screen.width<850?n:""},s.a.createElement(u["a"],{className:"list-group-item-icon"})," Months")),s.a.createElement(c["a"],null,s.a.createElement(i["Link"],{to:"/room",className:"/room"===window.location.pathname?"active-catalog":"nav-link",onClick:window.screen.width<850?n:""},s.a.createElement(p["a"],{className:"list-group-item-icon"})," xonalar")),s.a.createElement(c["a"],null,s.a.createElement(i["Link"],{to:"/product",className:"/product"===window.location.pathname?"active-catalog":"nav-link",onClick:window.screen.width<850?n:""},s.a.createElement(E["a"],{className:"list-group-item-icon"})," Mahsulotlar")),s.a.createElement(c["a"],null,s.a.createElement(i["Link"],{to:"/productKg",className:"/productKg"===window.location.pathname?"active-catalog":"nav-link",onClick:window.screen.width<850?n:""},s.a.createElement(E["a"],{className:"list-group-item-icon"})," Mahsulotlar Kg")),s.a.createElement(c["a"],{className:l?"d-block":"d-none"},s.a.createElement(i["Link"],{to:"/setting",className:"/setting"===window.location.pathname?"active-catalog":"nav-link",onClick:window.screen.width<850?n:""},s.a.createElement(h["a"],{className:"list-group-item-icon"})," Sozlash")))))}})||n),v=j;class g extends o["Component"]{render(){return s.a.createElement("div",{className:"catalog"},s.a.createElement(v,{pathname:this.props.pathname}),this.props.children)}}t["a"]=g},RnhZ:function(e,t,a){var l={"./af":"K/tc","./af.js":"K/tc","./ar":"jnO4","./ar-dz":"o1bE","./ar-dz.js":"o1bE","./ar-kw":"Qj4J","./ar-kw.js":"Qj4J","./ar-ly":"HP3h","./ar-ly.js":"HP3h","./ar-ma":"CoRJ","./ar-ma.js":"CoRJ","./ar-sa":"gjCT","./ar-sa.js":"gjCT","./ar-tn":"bYM6","./ar-tn.js":"bYM6","./ar.js":"jnO4","./az":"SFxW","./az.js":"SFxW","./be":"H8ED","./be.js":"H8ED","./bg":"hKrs","./bg.js":"hKrs","./bm":"p/rL","./bm.js":"p/rL","./bn":"kEOa","./bn-bd":"loYQ","./bn-bd.js":"loYQ","./bn.js":"kEOa","./bo":"0mo+","./bo.js":"0mo+","./br":"aIdf","./br.js":"aIdf","./bs":"JVSJ","./bs.js":"JVSJ","./ca":"1xZ4","./ca.js":"1xZ4","./cs":"PA2r","./cs.js":"PA2r","./cv":"A+xa","./cv.js":"A+xa","./cy":"l5ep","./cy.js":"l5ep","./da":"DxQv","./da.js":"DxQv","./de":"tGlX","./de-at":"s+uk","./de-at.js":"s+uk","./de-ch":"u3GI","./de-ch.js":"u3GI","./de.js":"tGlX","./dv":"WYrj","./dv.js":"WYrj","./el":"jUeY","./el.js":"jUeY","./en-au":"Dmvi","./en-au.js":"Dmvi","./en-ca":"OIYi","./en-ca.js":"OIYi","./en-gb":"Oaa7","./en-gb.js":"Oaa7","./en-ie":"4dOw","./en-ie.js":"4dOw","./en-il":"czMo","./en-il.js":"czMo","./en-in":"7C5Q","./en-in.js":"7C5Q","./en-nz":"b1Dy","./en-nz.js":"b1Dy","./en-sg":"t+mt","./en-sg.js":"t+mt","./eo":"Zduo","./eo.js":"Zduo","./es":"iYuL","./es-do":"CjzT","./es-do.js":"CjzT","./es-mx":"tbfe","./es-mx.js":"tbfe","./es-us":"Vclq","./es-us.js":"Vclq","./es.js":"iYuL","./et":"7BjC","./et.js":"7BjC","./eu":"D/JM","./eu.js":"D/JM","./fa":"jfSC","./fa.js":"jfSC","./fi":"gekB","./fi.js":"gekB","./fil":"1ppg","./fil.js":"1ppg","./fo":"ByF4","./fo.js":"ByF4","./fr":"nyYc","./fr-ca":"2fjn","./fr-ca.js":"2fjn","./fr-ch":"Dkky","./fr-ch.js":"Dkky","./fr.js":"nyYc","./fy":"cRix","./fy.js":"cRix","./ga":"USCx","./ga.js":"USCx","./gd":"9rRi","./gd.js":"9rRi","./gl":"iEDd","./gl.js":"iEDd","./gom-deva":"qvJo","./gom-deva.js":"qvJo","./gom-latn":"DKr+","./gom-latn.js":"DKr+","./gu":"4MV3","./gu.js":"4MV3","./he":"x6pH","./he.js":"x6pH","./hi":"3E1r","./hi.js":"3E1r","./hr":"S6ln","./hr.js":"S6ln","./hu":"WxRl","./hu.js":"WxRl","./hy-am":"1rYy","./hy-am.js":"1rYy","./id":"UDhR","./id.js":"UDhR","./is":"BVg3","./is.js":"BVg3","./it":"bpih","./it-ch":"bxKX","./it-ch.js":"bxKX","./it.js":"bpih","./ja":"B55N","./ja.js":"B55N","./jv":"tUCv","./jv.js":"tUCv","./ka":"IBtZ","./ka.js":"IBtZ","./kk":"bXm7","./kk.js":"bXm7","./km":"6B0Y","./km.js":"6B0Y","./kn":"PpIw","./kn.js":"PpIw","./ko":"Ivi+","./ko.js":"Ivi+","./ku":"JCF/","./ku.js":"JCF/","./ky":"lgnt","./ky.js":"lgnt","./lb":"RAwQ","./lb.js":"RAwQ","./lo":"sp3z","./lo.js":"sp3z","./lt":"JvlW","./lt.js":"JvlW","./lv":"uXwI","./lv.js":"uXwI","./me":"KTz0","./me.js":"KTz0","./mi":"aIsn","./mi.js":"aIsn","./mk":"aQkU","./mk.js":"aQkU","./ml":"AvvY","./ml.js":"AvvY","./mn":"lYtQ","./mn.js":"lYtQ","./mr":"Ob0Z","./mr.js":"Ob0Z","./ms":"6+QB","./ms-my":"ZAMP","./ms-my.js":"ZAMP","./ms.js":"6+QB","./mt":"G0Uy","./mt.js":"G0Uy","./my":"honF","./my.js":"honF","./nb":"bOMt","./nb.js":"bOMt","./ne":"OjkT","./ne.js":"OjkT","./nl":"+s0g","./nl-be":"2ykv","./nl-be.js":"2ykv","./nl.js":"+s0g","./nn":"uEye","./nn.js":"uEye","./oc-lnc":"Fnuy","./oc-lnc.js":"Fnuy","./pa-in":"8/+R","./pa-in.js":"8/+R","./pl":"jVdC","./pl.js":"jVdC","./pt":"8mBD","./pt-br":"0tRk","./pt-br.js":"0tRk","./pt.js":"8mBD","./ro":"lyxo","./ro.js":"lyxo","./ru":"lXzo","./ru.js":"lXzo","./sd":"Z4QM","./sd.js":"Z4QM","./se":"//9w","./se.js":"//9w","./si":"7aV9","./si.js":"7aV9","./sk":"e+ae","./sk.js":"e+ae","./sl":"gVVK","./sl.js":"gVVK","./sq":"yPMs","./sq.js":"yPMs","./sr":"zx6S","./sr-cyrl":"E+lV","./sr-cyrl.js":"E+lV","./sr.js":"zx6S","./ss":"Ur1D","./ss.js":"Ur1D","./sv":"X709","./sv.js":"X709","./sw":"dNwA","./sw.js":"dNwA","./ta":"PeUW","./ta.js":"PeUW","./te":"XLvN","./te.js":"XLvN","./tet":"V2x9","./tet.js":"V2x9","./tg":"Oxv6","./tg.js":"Oxv6","./th":"EOgW","./th.js":"EOgW","./tk":"Wv91","./tk.js":"Wv91","./tl-ph":"Dzi0","./tl-ph.js":"Dzi0","./tlh":"z3Vd","./tlh.js":"z3Vd","./tr":"DoHr","./tr.js":"DoHr","./tzl":"z1FC","./tzl.js":"z1FC","./tzm":"wQk9","./tzm-latn":"tT3J","./tzm-latn.js":"tT3J","./tzm.js":"wQk9","./ug-cn":"YRex","./ug-cn.js":"YRex","./uk":"raLr","./uk.js":"raLr","./ur":"UpQW","./ur.js":"UpQW","./uz":"Loxo","./uz-latn":"AQ68","./uz-latn.js":"AQ68","./uz.js":"Loxo","./vi":"KSF8","./vi.js":"KSF8","./x-pseudo":"/X5v","./x-pseudo.js":"/X5v","./yo":"fzPg","./yo.js":"fzPg","./zh-cn":"XDpg","./zh-cn.js":"XDpg","./zh-hk":"SatO","./zh-hk.js":"SatO","./zh-mo":"OmwH","./zh-mo.js":"OmwH","./zh-tw":"kOpN","./zh-tw.js":"kOpN"};function n(e){var t=o(e);return a(t)}function o(e){if(!a.o(l,e)){var t=new Error("Cannot find module '"+e+"'");throw t.code="MODULE_NOT_FOUND",t}return l[e]}n.keys=function(){return Object.keys(l)},n.resolve=o,e.exports=n,n.id="RnhZ"},"oj+c":function(e,t,a){"use strict";a.r(t);var l,n,o=a("p0pE"),s=a.n(o),r=a("q1tI"),c=a.n(r),i=a("/t9W"),m=a("MuoO"),d=a("iNdW"),u=a("v7au"),p=a("DCcX"),E=a("vkoW"),h=a("CaDr"),j=a("OBzv"),v=a("sOKU"),g=a("kJEY"),b=a("GUC0"),k=a.n(b),y=a("5v02"),w=a("CO4e"),N=(l=Object(m["connect"])(e=>{var t=e.globalModel,a=e.intoRoomModel;return{globalModel:t,intoRoomModel:a}}),l(n=class extends r["Component"]{componentDidMount(){this.props.dispatch({type:"intoRoomModel/getClient",payload:{dayId:localStorage.getItem(y["c"]),roomId:localStorage.getItem(y["g"])}}),this.props.dispatch({type:"intoRoomModel/getProductsKg"}),this.props.dispatch({type:"intoRoomModel/getProducts"})}constructor(e){super(e),this.state={summa:1e3,cost:0},this.handleChange=this.handleChange.bind(this),this.handleChange1=this.handleChange1.bind(this)}handleChange(e){this.setState({summa:e.replace(/ /gi,"")})}handleChange1(e){this.setState({cost:e.replace(/ /gi,"")})}render(){var e=this.props,t=e.dispatch,a=e.intoRoomModel,l=e.globalModel,n=l.isStaff,o=(l.isDirector,a.isOpenModal),r=a.serviceClient,m=a.isOpenModalSr,b=a.isOpenModalKg,y=a.client,N=a.clients,C=a.product,D=a.productKg,S=a.products,I=a.productsKg,M=(e,a)=>{t({type:"intoRoomModel/updateState",payload:{isOpenModalSr:!m,serviceClient:e,client:a}})},z=(e,a)=>{a.price=this.state.summa,a.cost=this.state.cost,t({type:"intoRoomModel/addServiceClient",payload:s()({clientId:y.id},a)})},f=e=>{k()({title:"siz rostan ham o'chirmoq chimisiz?",buttons:["yo'q","xa"]}).then(a=>{a&&t({type:"intoRoomModel/deleteServiceClient",payload:{id:e}})})},x=(e,a)=>{t({type:"intoRoomModel/updateState",payload:{isOpenModal:!o,product:e,client:a}})},O=(e,a)=>{t({type:"intoRoomModel/updateState",payload:{isOpenModalKg:!b,productKg:e,client:a}})},F=(e,a)=>{k()({title:"siz rostan ham o'chirmoq chimisiz?",buttons:["yo'q","xa"]}).then(l=>{l&&t({type:"intoRoomModel/deleteProClient",payload:{id:e,clientId:a.id}})})},A=(e,a)=>{k()({title:"siz rostan ham o'chirmoq chimisiz?",buttons:["yo'q","xa"]}).then(l=>{l&&t({type:"intoRoomModel/deleteProClientKg",payload:{id:e,clientId:a.id}})})},R=(e,a)=>{t({type:"intoRoomModel/saveProClient",payload:s()({clientId:y.id},a)})},q=(e,a)=>{t({type:"intoRoomModel/saveProClientKg",payload:s()({clientId:y.id},a)})},K=e=>{t({type:"intoRoomModel/sendCheck",payload:{id:e}})};return c.a.createElement("div",null,c.a.createElement(i["a"],{pathname:this.props.pathname},c.a.createElement("div",{className:"container pl-4"},N.length<1?c.a.createElement("div",{className:"row pt-5"},c.a.createElement("div",{className:"col-md-12 text-center"},c.a.createElement("div",{className:"lds-spinner"},c.a.createElement("div",null),c.a.createElement("div",null),c.a.createElement("div",null),c.a.createElement("div",null),c.a.createElement("div",null),c.a.createElement("div",null),c.a.createElement("div",null),c.a.createElement("div",null),c.a.createElement("div",null),c.a.createElement("div",null),c.a.createElement("div",null),c.a.createElement("div",null)))):N.map((e,t)=>c.a.createElement("div",{key:t,className:"row pt-4"},c.a.createElement("div",{className:"col-md-12"},c.a.createElement("div",{className:"scroll-hide"},c.a.createElement("table",{className:"table table-striped"},c.a.createElement("thead",null,c.a.createElement("tr",null,c.a.createElement("th",null,"ismi"),c.a.createElement("th",null,"telefon"),c.a.createElement("th",null,"vaqti"),c.a.createElement("th",null,"xolati"))),c.a.createElement("tbody",null,c.a.createElement("tr",null,c.a.createElement("td",null,c.a.createElement("h5",null,e.name)),c.a.createElement("td",null,c.a.createElement("h5",null,e.telNum)),c.a.createElement("td",null,c.a.createElement("h5",null,void 0!==e.registerAt?e.registerAt.substring(0,16).replace("T",","):"")),c.a.createElement("td",null,c.a.createElement("h5",null,e.status))))))),c.a.createElement("div",{className:"col-md-12"},c.a.createElement("button",{className:"FINISHED"===e.status||(new Date).toLocaleDateString().replaceAll("/","-")!==e.bookedDate?"d-none":"btn btn-primary",onClick:()=>x("",e)},"mahsulot qoshish"),c.a.createElement("div",{className:"pt-md-3 scroll-hide"},c.a.createElement("table",{className:"table table-striped"},c.a.createElement("thead",null,c.a.createElement("tr",null,c.a.createElement("th",null,"nomi"),c.a.createElement("th",null,"dona"),c.a.createElement("th",null,"summa"),c.a.createElement("th",{colSpan:"2"},"action"))),c.a.createElement("tbody",null,e.productClients.map(t=>c.a.createElement("tr",{key:t.productClientId},c.a.createElement("td",null,t.name),c.a.createElement("td",null,t.amount),c.a.createElement("td",null,new Intl.NumberFormat("en-EN").format(t.balance)),c.a.createElement("td",null,"FINISHED"===e.status||(new Date).toLocaleDateString().replaceAll("/","-")!==e.bookedDate?c.a.createElement(d["a"],null):c.a.createElement(d["a"],{onClick:()=>x(t,e)})),c.a.createElement("td",null,"FINISHED"===e.status||(new Date).toLocaleDateString().replaceAll("/","-")!==e.bookedDate?c.a.createElement(u["a"],null):c.a.createElement(u["a"],{onClick:()=>F(t.productClientId,e)})))))))),c.a.createElement("div",{className:"col-md-12"},c.a.createElement("button",{className:"FINISHED"===e.status||(new Date).toLocaleDateString().replaceAll("/","-")!==e.bookedDate?"d-none":"btn btn-primary",onClick:()=>O("",e)},"kg mahsulot qoshish"),c.a.createElement("div",{className:"pt-md-3 scroll-hide"},c.a.createElement("table",{className:"table table-striped"},c.a.createElement("thead",null,c.a.createElement("tr",null,c.a.createElement("th",null,"nomi"),c.a.createElement("th",null,"kg"),c.a.createElement("th",null,"summa"),c.a.createElement("th",{colSpan:"2"},"action"))),c.a.createElement("tbody",null,e.productKgClients.map(t=>c.a.createElement("tr",{key:t.productClientId},c.a.createElement("td",null,t.name),c.a.createElement("td",null,new Intl.NumberFormat("en-EN").format(t.amount)),c.a.createElement("td",null,new Intl.NumberFormat("en-EN").format(t.balance)),c.a.createElement("td",null,"FINISHED"===e.status||(new Date).toLocaleDateString().replaceAll("/","-")!==e.bookedDate?c.a.createElement(d["a"],null):c.a.createElement(d["a"],{onClick:()=>O(t,e)})),c.a.createElement("td",null,"FINISHED"===e.status||(new Date).toLocaleDateString().replaceAll("/","-")!==e.bookedDate?c.a.createElement(u["a"],null):c.a.createElement(u["a"],{onClick:()=>A(t.productClientId,e)})))))))),c.a.createElement("div",{className:"col-md-12"},c.a.createElement("button",{className:"FINISHED"===e.status||(new Date).toLocaleDateString().replaceAll("/","-")!==e.bookedDate?"d-none":"btn btn-primary",onClick:()=>M("",e)},"xizmat qoshish"),c.a.createElement("div",{className:"pt-md-3 scroll-hide"},c.a.createElement("table",{className:"table table-striped"},c.a.createElement("thead",null,c.a.createElement("tr",null,c.a.createElement("th",null,"nomi"),c.a.createElement("th",null,"summa"),c.a.createElement("th",{colSpan:"2"},"action"))),c.a.createElement("tbody",null,e.resServiceClients.map(t=>c.a.createElement("tr",{key:t.id},c.a.createElement("td",null,t.definition),c.a.createElement("td",null,new Intl.NumberFormat("en-EN").format(t.price)),c.a.createElement("td",null,"FINISHED"===e.status||(new Date).toLocaleDateString().replaceAll("/","-")!==e.bookedDate&&n?c.a.createElement(d["a"],null):c.a.createElement(d["a"],{onClick:()=>M(t,e)})),c.a.createElement("td",null,"FINISHED"===e.status||(new Date).toLocaleDateString().replaceAll("/","-")!==e.bookedDate?c.a.createElement(u["a"],null):c.a.createElement(u["a"],{onClick:()=>f(t.id)})))))))),c.a.createElement("div",{className:"col-md-3 offset-4"},c.a.createElement("h5",null,"total sum ",new Intl.NumberFormat("en-EN").format(e.productKgTotalSum+e.productTotalSum+e.serviceClientTotalSum))),c.a.createElement("div",{className:"col-md-2"},c.a.createElement("button",{onClick:()=>K(e.id),className:"FINISHED"===e.status||(new Date).toLocaleDateString().replaceAll("/","-")!==e.bookedDate?"d-none":"ml-md-2 btn btn-success"},"yopish")),c.a.createElement("hr",{className:"bg-dark"}))))),c.a.createElement(p["a"],{isOpen:o,toggle:x},c.a.createElement(E["a"],null,c.a.createElement("div",null,c.a.createElement("h5",null,"Mahsulot qo'shish"))),c.a.createElement(g["AvForm"],{onValidSubmit:R},c.a.createElement("div",null,c.a.createElement(h["a"],null,c.a.createElement(g["AvField"],{name:"productClientId",type:"text",className:"d-none",value:C?C.productClientId:""}),c.a.createElement(g["AvField"],{name:"productId",type:"select",value:C?C.productId:"",required:!0},c.a.createElement("option",{value:"",disabled:!C},C?C.name:"mahsulot tanlang"),S.map(e=>c.a.createElement("option",{key:e.id,value:e.id},e.name))),c.a.createElement(g["AvField"],{name:"amount",type:"select",value:C?C.amount:"",required:!0},c.a.createElement("option",{value:"",disabled:!C},"miqdorini tanlang"),c.a.createElement("option",{value:"1"},"1"),c.a.createElement("option",{value:"2"},"2"),c.a.createElement("option",{value:"3"},"3"),c.a.createElement("option",{value:"4"},"4"),c.a.createElement("option",{value:"5"},"5"),c.a.createElement("option",{value:"6"},"6"),c.a.createElement("option",{value:"7"},"7"),c.a.createElement("option",{value:"8"},"8"),c.a.createElement("option",{value:"9"},"9"),c.a.createElement("option",{value:"10"},"10"))),c.a.createElement(j["a"],null,c.a.createElement(v["a"],{type:"button",color:"danger",onClick:()=>x("","")},"yopish"),c.a.createElement(v["a"],{color:"success"},"saqlash"))))),c.a.createElement(p["a"],{isOpen:b,toggle:O},c.a.createElement(E["a"],null,c.a.createElement("div",null,c.a.createElement("h5",null,"Mahsulot Kg qo'shish"))),c.a.createElement(g["AvForm"],{onValidSubmit:q},c.a.createElement("div",null,c.a.createElement(h["a"],null,c.a.createElement(g["AvField"],{name:"productClientId",type:"text",className:"d-none",value:D?D.productClientId:""}),c.a.createElement(g["AvField"],{name:"productId",type:"select",value:D?D.productId:"",required:!0},c.a.createElement("option",{value:"",disabled:!D},D?D.name:"mahsulot tanlang"),I.map(e=>c.a.createElement("option",{key:e.id,value:e.id},e.name))),c.a.createElement(g["AvField"],{name:"amount",type:"select",value:D?D.amount:"",required:!0},c.a.createElement("option",{value:"",disabled:!D},"miqdorini tanlang"),c.a.createElement("option",{value:"100"},"100"),c.a.createElement("option",{value:"200"},"200"),c.a.createElement("option",{value:"300"},"300"),c.a.createElement("option",{value:"400"},"400"),c.a.createElement("option",{value:"500"},"500"),c.a.createElement("option",{value:"600"},"600"),c.a.createElement("option",{value:"700"},"700"),c.a.createElement("option",{value:"800"},"800"),c.a.createElement("option",{value:"900"},"900"),c.a.createElement("option",{value:"1000"},"1000"))),c.a.createElement(j["a"],null,c.a.createElement(v["a"],{type:"button",color:"danger",onClick:()=>O("","")},"yopish"),c.a.createElement(v["a"],{color:"success"},"saqlash"))))),c.a.createElement(p["a"],{isOpen:m,toggle:M},c.a.createElement(E["a"],null),c.a.createElement(g["AvForm"],{onValidSubmit:z},c.a.createElement("div",null,c.a.createElement(h["a"],null,c.a.createElement(g["AvField"],{name:"id",className:"d-none",value:r?r.id:""}),c.a.createElement(g["AvField"],{name:"definition",value:r?r.definition:"",label:"nima uchun",placeholder:"nima uchunligini yozing"}),c.a.createElement(g["AvField"],{className:"w-100 border-secondary rounded",maxLength:"7",tag:w["a"],precision:"0",label:"sotilish narxi",thousandSeparator:" ",name:"price",onChange:this.handleChange.bind(this),placeholder:"enter payment sum",required:!0}),c.a.createElement(g["AvField"],{className:"w-100 border-secondary rounded",maxLength:"7",tag:w["a"],precision:"0",label:"tan narxi",thousandSeparator:" ",name:"cost",onChange:this.handleChange1.bind(this),placeholder:"enter payment sum"})),c.a.createElement(j["a"],null,c.a.createElement(v["a"],{type:"button",color:"danger",onClick:()=>M("","")},"yopish"),c.a.createElement(v["a"],{color:"success"},"saqlash"))))))}})||n);t["default"]=N}}]);