<template>
<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
    <div v-for="movie in movies" :key="movie.movie_id">
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleCaptions" :data-bs-slide-to="movie.movie_id" :class="[movie.movie_id == index ? 'active' : '']" :aria-current="[movie.movie_id == index ? 'true' : '']" :aria-label="`Slide ${movie.movie_id}`"></button>
    </div>
    <div class="carousel-inner">
        <div :class="[movie.movie_id == index ? 'active' : '','carousel-item']">
            <img :src="movie.movie_picture" class="d-block w-100" :alt="movie.movie_cn_name">
        </div>
    </div>
    </div>
    <button class="carousel-control-prev" @click="prev()" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" @click="next()" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>
</template>

<script>
    export default {
        name: 'Carousel',
        props: {
            movies: Array,
        },
        data () {
            return {
                index: 1,
                polling: null
            }
        },
        methods: {
            prev() {
                if(this.index == 1) {
                    this.index = this.movies.length
                } else {
                    this.index --
                }
            },
            next() {
                if(this.index == this.movies.length) {
                    this.index = 1
                } else {
                    this.index ++
                }
            },
            play() {
                this.polling = setInterval(this.next, 3000)
            }
        },
        created () {
            this.play()
        }
    }
</script>

<style scoped>
    img {
        height: 500px;
    }
</style>