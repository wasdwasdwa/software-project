<template>
    <div class="card mb-4">
        <img :src="movie.movie_picture" class="card-img-top" :alt="movie.movie_cn_name">
        <div class="card-body" style="text-align: end;">
            <h5 class="card-title" style="overflow: hidden; height: 20px"><b>{{movie.movie_cn_name}}</b></h5>
            <p class="card-text" style="text-align: center;overflow: hidden; height: 150px;">{{movie.movie_detail}}</p>
            <a @click="getTimes(movie.movie_id,cid)" class="btn btn-dark text-white mt-2">
            <svg  xmlns="http://www.w3.org/2000/svg" v-if="movie.movie_state" width="16" height="16" fill="currentColor" class="bi bi-cart3" viewBox="0 0 16 16">
            <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </svg>
            <svg xmlns="http://www.w3.org/2000/svg" v-else width="16" height="16" fill="currentColor" class="bi bi-three-dots" viewBox="0 0 16 16">
            <path d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z"/>
            </svg></a>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Movieview',
    props: {
        movie: Object,
        cid: {
            type: Number,
            default: 0
        }
    },
    methods: {
        getTimes(mid,cid) {
            this.$router.push({
                name: 'Times',
                query: {
                    mid: mid,
                    cid: cid
                }
            }) 
        }
    }
}
</script>

<style scoped>
    img {
        height: 300px;
    }
</style>
