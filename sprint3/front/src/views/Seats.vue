<template>
    <div>
        <nav id="navbar-example2" class="navbar navbar-light bg-light px-3 mb-3">
            <a class="navbar-brand" >{{movie.name}}</a>
            <!-- <ul class="nav nav-pills">
                <li class="nav-item">
                <a class="nav-link text-dark fs-4 position-absolute top-50 start-50 translate-middle" href="#">{{movie.name}}</a>
                </li>
            </ul> -->
        </nav>
        <div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-offset="0" tabindex="0">
            <div class="row">
                <div class="col-sm-5 col-lg-5 col-md-5">
                    <img :src="movie.poster" class="d-block w-100" :alt="movie.name">
                </div>
                <div class="col-sm-7 col-lg-7 col-md-7">
                    <h4>Brief description:</h4>
                    <p>{{movie.description}}</p>
                    <h4>Movie director:</h4>
                    <p>{{movie.description}}</p>
                    <h4>Main actors:</h4>
                    <p>{{movie.description}}</p>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col-sm-8 col-lg-8 col-md-8">
                <div v-for="seat in seats" :key="seat.id">
                <Seat @seatSelected="select($event)" :seat="seat" :mid="mid" :cid="cid" :did="did"/>
                </div>
                </div>
                <a @click="getOrder(mid,cid,did)" class="btn btn-dark text-white mt-2">Confirm!</a>  
            </div>
        </div>
    </div>
</template>

<script>
import Seat from '../components/Seat'

export default {
    name: 'Seats',
    components: {
        Seat
    },
    data() {
        return {
            mid: parseInt(this.$route.query.mid),
            cid: parseInt(this.$route.query.cid),
            did: parseInt(this.$route.query.did),
            movie: Object,
            seats: Array,
            selected: []
        }
    },
    methods: {
        async select(msg) {
            let id = msg;
            const seatSelected = await this.fetchSeat(this.mid,this.cid,this.did,id)
            const upDate = {...seatSelected,isSelected: !seatSelected.isSelected}

            if(upDate.isSelected) {
                this.selected = [...this.selected,upDate]
            } else {
                this.selected = this.selected.filter((seat) => seat.id !== upDate.id)
            }

            const res = await fetch(`api/seatsM${this.mid}C${this.cid}D${this.did}/${id}`,{
                method: 'PUT',
                headers: {
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(upDate)
            })
            // const res = await fetch('api/seatsMCDS',{
            //     method: 'PUT',
            //     headers: {
            //         'Content-type': 'application/json'
            //     },
            //     body: JSON.stringify(upDate,this.mid,this.cid,this.did,id)
            // })

            const data = await res.json()

            this.seats = this.seats.map((seat)=>seat.id === id ? {...seat,isSelected:data.isSelected}:seat
            )
        },
        async fetchMovie(mid) {
            const res = await fetch(`api/movies/${mid}`)
            // const res = await fetch('api/movies',{
            //     method: 'GET',
            //     headers: {
            //         'Content-type': 'application/json',
            //     },
            //     body: JSON.stringify(mid)
            // })

            const data = await res.json()

            return data
        },
        async fetchSeats(mid,cid,did) {
            const res = await fetch(`api/seatsM${mid}C${cid}D${did}`)
            // const res = await fetch('api/seatsMCD',{
            //     method: 'GET',
            //     headers: {
            //         'Content-type': 'application/json',
            //     },
            //     body: JSON.stringify(mid,cid,did)
            // })

            const data = await res.json()

            return data
        },
        async fetchSeat(mid,cid,did,sid) {
            const res = await fetch(`api/seatsM${mid}C${cid}D${did}/${sid}`)
            // const res = await fetch('api/seatsMCDS',{
            //     method: 'GET',
            //     headers: {
            //         'Content-type': 'application/json',
            //     },
            //     body: JSON.stringify(mid,cid,did,sid)
            // })

            const data = await res.json()

            return data
        },
        async getOrder(mid,cid,did) {
            this.$router.push({
                name: 'Orders',
                query: {
                    mid: mid,
                    cid: cid,
                    did: did,
                    sel: this.selected
                }
            })  
        }
    },
    async created () {
        this.movie = await this.fetchMovie(this.$route.query.mid)
        this.seats = await this.fetchSeats(this.$route.query.mid,this.$route.query.cid,this.$route.query.did)
    }
}
</script>

<style scoped>
    Seat{
        float: left
    }
</style>
