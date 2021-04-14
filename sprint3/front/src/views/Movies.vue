<template>
    <div>
        <nav id="navbar-example2" class="navbar navbar-light bg-light px-3 mb-3">
            <a class="navbar-brand" >All Movies</a>
            <ul class="nav nav-pills">
                <li class="nav-item">
                <a class="nav-link bg-dark text-light" href="#">More Coming Soon...</a>
                </li>
            </ul>
        </nav>
        <div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-offset="0" tabindex="0">
            <div class="row justify-content-md-center">
                <div class="col-sm-4 col-lg-4 col-md-4" v-for="movie in movies" :key="movie.id">
                <Movieview :movie="movie" :cid="cid"/>
            </div>
        </div>
        </div>
    </div>
</template>

<script>
import Movieview from '../components/Movieview'

export default {
    name: 'Movies',
    components: {
        Movieview
    },
    data () {
        return {
            movies: [],
            cid: parseInt(this.$route.query.id)
        }
    },
    methods: {
        async fetchMoviesWithCid(cid) {
            const res = await fetch(`api/cinema${cid}Movies`)
            // const res = await fetch('api/cinema',{
            //     method: 'GET',
            //     headers: {
            //         'Content-type': 'application/json',
            //     },
            //     body: JSON.stringify(cid),
            // })

            const data = await res.json()

            return data
        }
    },
    async created () {
        this.movies = await this.fetchMoviesWithCid(this.$route.query.id)
    },
}
</script>