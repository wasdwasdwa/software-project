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
    },
    {
        path: '/cinemas',
        name: 'Cinemas',
        component: Cinemas,
    },
    {
        path: '/movies',
        name: 'Movies',
        component: Movies,
    },
    {
        path: '/times',
        name: 'Times',
        component: Times,
    },
    {
        path: '/seats',
        name: 'Seats',
        component: Seats,
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router