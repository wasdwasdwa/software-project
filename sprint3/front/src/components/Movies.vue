<template>
<div>
    <div class="card">
        <div class="card-header text-dark bg-light fw-bold fs-5">
            Continuing Movies
            <router-link to="/cinemas" class="btn btn-dark btn-outline-light">See Cinemas</router-link>
        </div>
        <div class="card-body">
            <div class="col-sm-6 col-lg-2 col-md-2" style="float: left;"  v-for="movie in movies" :key="movie.id">
                <Movie v-if="movie.state" :movie="movie"/>
            </div>
        </div>
    </div>
    <div class="card">
        <div class="card-header text-dark bg-light fw-bold fs-5">
            Coming Soon...
            <router-link to="/cinemas" class="btn btn-dark btn-outline-light">See Cinemas</router-link>
        </div>
        <div class="card-body">
            <div class="col-sm-2 col-lg-2 col-md-2" style="float: left;" v-for="movie in movies" :key="movie.id">
                <Movie v-if="!movie.state" :movie="movie"/>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import Movie from './Movie.vue'

export default {
    name: 'Movies',
    props: {
        movies: Array,
    },
    components: { 
        Movie,
    },
}
</script>

<style scoped>
    .card-body {
        overflow: auto;
        height: 340px;
    }
</style>
