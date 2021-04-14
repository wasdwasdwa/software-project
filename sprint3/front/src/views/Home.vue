<template>
<div>
    <Carousel :movies="movies"/>
    <Movies :movies="movies"/>
</div>
</template>

<script>
import Carousel from '../components/Carousel.vue'
import Movies from '../components/Movies.vue'

export default {
    name: 'Home',
    components: {
        Carousel,
        Movies
    },
    data () {
        return {
            movies: []
        }
    },
    methods: {
        async fetchMovies() {
            const res = await fetch('api/movies')

            const data = await res.json()

            return data
        },
        async fetchMovie(id) {
            const res = await fetch(`api/movies/${id}`)
            // const res = await fetch('api/movies',{
            //     method: 'GET',
            //     headers: {
            //         'Content-type': 'application/json',
            //     },
            //     body: JSON.stringify(id)
            // })

            const data = await res.json()

            return data
        },
    },
    async created () {
        this.movies = await this.fetchMovies()
    },
}
</script>
