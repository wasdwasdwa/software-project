import { createStore } from 'vuex'

const store = createStore({
    state: {
            isLogin: false,
            usname : ''
    },
    getters: {
        isLogin: state => state.isLogin,
        usname: state => state.usname
    }, 
    mutations: {
        userStatus(state, flag) {
            state.isLogin = flag
        },
        nameStatus(state, name) {
            state.usname = name
        }
    },
    actions: {
        userLogin({commit}, flag, name) {
            commit("userStatus", flag),
            commit("nameStatus",name)
        }
    }
})

export default store
