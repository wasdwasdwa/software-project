import { createApp } from 'vue'
import App from './App.vue'
import "bootstrap/dist/css/bootstrap.min.css";
import 'bootstrap';
import router from './router'
import store from './store'

createApp(App).use(router).use(store).mount('#app')

router.beforeEach((to, from, next) => {
    let getFlag = localStorage.getItem("Flag");
    if(getFlag === "isLogin"){
        store.state.isLogin = true
        store.state.usname = localStorage.getItem("Uname")

        if (to.path == '/login') {
            localStorage.removeItem("Flag")
            localStorage.removeItem("Uname")
            alert("Log out!")
            next({
                path: '/'
            })
        }

        next()
    }else{
        if(to.meta.isLogin){
            next({
                path: '/login',
            })
            alert('Please login')
        }else{
            next()
        }
    }
});

