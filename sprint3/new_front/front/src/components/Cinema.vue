<template>
    <div class="card border-dark mb-1">
        <div class="card-header text-dark bg-light fs-5">{{cinema.cinema_name}}
            <a v-if="mid == 0" @click="getMovies(cinema.cinema_id)" class="btn btn-dark btn-outline-light" style="float: right;">See More</a>
            <a v-else @click="getTimes(mid,cinema.cinema_id)" class="btn btn-dark btn-outline-light" style="float: right;">Select Time</a>
        </div>
        <div class="card-body text-dark">
            <p class="card-text text-dark fs-6">{{cinema.cinema_address}}</p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Cinema',
    props: {
        cinema: Object,
        mid: {
            type: Number,
            default: 0
        }
    },
    methods: {
        getMovies(id) {
            this.$router.push({
                name: 'Movies',
                query: {
                    id: id
                }
            })        
        },
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
