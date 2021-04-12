<template>
<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
    <div v-for="movie in movies" :key="movie.id">
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleCaptions" :data-bs-slide-to="movie.id" :class="[movie.id == index ? 'active' : '']" :aria-current="[movie.id == index ? 'true' : '']" :aria-label="`Slide ${movie.id}`"></button>
    </div>
    <div class="carousel-inner">
        <div :class="[movie.id == index ? 'active' : '','carousel-item']">
            <img :src="movie.poster" class="d-block w-100" :alt="movie.name">
            <!-- <div class="carousel-caption d-none d-md-block">
                <h5>{{movie.name}}</h5>
                <p>{{movie.description}}</p>
            </div> -->
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