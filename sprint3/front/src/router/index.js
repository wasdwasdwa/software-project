<<<<<<< HEAD
import {createRouter, createWebHistory} from 'vue-router'
import Home from '../views/Home'
import Cinemas from '../views/Cinemas'
import Movies from '../views/Movies'
import Times from '../views/Times'
import Seats from '../views/Seats'

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        meta: {
            isLogin: false
        }
    },
    {
        path: '/cinemas',
        name: 'Cinemas',
        component: Cinemas,
        meta: {
            isLogin: false
        }
    },
    {
        path: '/movies',
        name: 'Movies',
        component: Movies,
        meta: {
            isLogin: false
        }
    },
    {
        path: '/times',
        name: 'Times',
        component: Times,
        meta: {
            isLogin: false
        }
    },
    {
        path: '/seats',
        name: 'Seats',
        component: Seats,
        meta: {
            isLogin: false
        }
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

=======
import {createRouter, createWebHistory} from 'vue-router'
import Home from '../views/Home'
import Cinemas from '../views/Cinemas'
import Movies from '../views/Movies'
import Times from '../views/Times'
import Seats from '../views/Seats'

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        meta: {
            isLogin: false
        }
    },
    {
        path: '/cinemas',
        name: 'Cinemas',
        component: Cinemas,
        meta: {
            isLogin: false
        }
    },
    {
        path: '/movies',
        name: 'Movies',
        component: Movies,
        meta: {
            isLogin: false
        }
    },
    {
        path: '/times',
        name: 'Times',
        component: Times,
        meta: {
            isLogin: false
        }
    },
    {
        path: '/seats',
        name: 'Seats',
        component: Seats,
        meta: {
            isLogin: false
        }
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

>>>>>>> 49c71a4927b09b1425c2e9e686d5ef2a7e2154c0
export default router