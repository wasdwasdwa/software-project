<template>
    <div>
        <nav id="navbar-example2" class="navbar navbar-light bg-light px-3 mb-3">
            <a class="navbar-brand" >{{movie.name}}</a>
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
                    <p>{{movie.movie_director}}</p>
                    <h4>Main actors:</h4>
                    <p>{{movie.movie_actor}}</p>
                    <h4>Time:</h4>
                    <p>{{hall.time}}</p>
                    <h4>Hall:</h4>
                    <p>{{hall.hall}}</p>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col-sm-8 col-lg-8 col-md-8">
                    <div v-for="seat in seats" :key="seat.id">
                    <Seat @seatSelected="select($event)" :seat="seat" :mid="mid" :cid="cid" :did="did"/>
                    </div>
                </div>
                <Order @order="getOrder()" @unselect="unselect()" v-if="selected.length!=0" :movie="movie" :hall="hall" :cinema="cinema" :selected="selected"/>
            </div>
        </div>
    </div>
</template>

<script>
import Seat from '../components/Seat'
import Order from '../components/Order'
import Axios from 'axios'

export default {
    name: 'Seats',
    components: {
        Seat,
        Order
    },
    data() {
        return {
            mid: parseInt(this.$route.query.mid),
            cid: parseInt(this.$route.query.cid),
            did: parseInt(this.$route.query.did),
            tid: parseInt(this.$route.query.tid),
            movie: Object,
            cinema: Object,
            seats: Array,
            hall: Object,
            selected: []
        }
    },
    methods: {
        async getOrder() {
            for(let i = 0; i < this.selected.length;i ++) {
                let id = this.selected[i].id
                const seatOrdered = await this.fetchSeat(this.mid,this.cid,this.did,this.did,id)
                const upDate = {...seatOrdered,isAvailable: !seatOrdered.isAvailable}
                let data

                Axios.put('http://localhost:8181/seatsMCTD', {
                    params:{ 
                        mid: this.mid,
                        cid: this.cid,
                        tid: this.tid,
                        did: this.did,
                        sid: id
                    },
                    upDate
                }).then(function(res){
                    data = res.data
                }).catch(function(error){
                    console.log(error)
                });

                // const res = await fetch(`api/seatsM${this.mid}C${this.cid}T${this.tid}D${this.did}/${id}`,{
                // method: 'PUT',
                // headers: {
                //     'Content-type': 'application/json'
                // },
                // body: JSON.stringify(upDate)
                // })

                // const data = await res.json()

                this.seats = this.seats.map((seat)=>seat.id === id ? {...seat,isAvailable:data.isAvailable}:seat
                )
            }
        },
        async select(msg) {
            let id = msg;
            const seatSelected = await this.fetchSeat(this.mid,this.cid,this.tid,this.did,id)
            const upDate = {...seatSelected,isSelected: !seatSelected.isSelected}

            let data

            if(upDate.isSelected) {
                this.selected = [...this.selected,upDate]
            } else {
                this.selected = this.selected.filter((seat) => seat.id !== upDate.id)
            }

            Axios.put('http://localhost:8181/seatsMCTD', {
                params:{ 
                    mid: this.mid,
                    cid: this.cid,
                    tid: this.tid,
                    did: this.did,
                    sid: id
                },
                upDate
            }).then(function(res){
                data = res.data
            }).catch(function(error){
                console.log(error)
            });

            // const res = await fetch(`api/seatsM${this.mid}C${this.cid}T${this.tid}D${this.did}/${id}`,{
            //     method: 'PUT',
            //     headers: {
            //         'Content-type': 'application/json'
            //     },
            //     body: JSON.stringify(upDate)
            // })

            // const data = await res.json()

            this.seats = this.seats.map((seat)=>seat.id === id ? {...seat,isSelected:data.isSelected}:seat
            )
        },
        async unselect() {
            for(let i = 0; i < this.selected.length;i ++) {
                let id = this.selected[i].id
                const seatUnselect = await this.fetchSeat(this.mid,this.cid,this.tid,this.did,id)
                const upDate = {...seatUnselect,isSelected: !seatUnselect.isSelected}

                let data

                Axios.put('http://localhost:8181/seatsMCTD', {
                    params:{ 
                        mid: this.mid,
                        cid: this.cid,
                        tid: this.tid,
                        did: this.did,
                        sid: id
                    },
                    upDate
                }).then(function(res){
                    data = res.data
                }).catch(function(error){
                    console.log(error)
                });

                // const res = await fetch(`api/seatsM${this.mid}C${this.cid}T${this.tid}D${this.did}/${id}`,{
                // method: 'PUT',
                // headers: {
                //     'Content-type': 'application/json'
                // },
                // body: JSON.stringify(upDate)
                // })

                // const data = await res.json()

                this.seats = this.seats.map((seat)=>seat.id === id ? {...seat,isSelected:data.isSelected}:seat
                )
                this.selected = this.selected.filter((seat) => seat.id !== id)
            }
        },
        async fetchMovie(mid) {
            const _this=this
            Axios.get('http://localhost:8181/movies',{
                params:{
                    mid: mid
                }
            }).then(function(res){
                _this.movie = res.data;
            }).catch(function (error) {
                console.log(error);
            });

            // const res = await fetch(`api/movies/${mid}`)

            // const data = await res.json()

            // return data
        },
        async fetchCinema(cid) {
            const _this=this
            Axios.get('http://localhost:8181/cinemas',{
                params:{
                    cid: cid
                }
            }).then(function(res){
                _this.cinema = res.data;
            }).catch(function (error) {
                console.log(error);
            });

            // const res = await fetch(`api/cinemas/${cid}`)

            // const data = await res.json()

            // return data
        },
        async fetchHall(mid,cid,tid,did) {
            const _this=this
            Axios.get('http://localhost:8181/movieCinemaTime',{
                params:{
                    mid: mid,
                    cid: cid,
                    tid: tid,
                    did: did
                }
            }).then(function(res){
                _this.hall = res.data;
            }).catch(function (error) {
                console.log(error);
            });

            // const res = await fetch(`api/movie${mid}Cinema${cid}Time${tid}/${did}`)

            // const data = await res.json()

            // return data
        },
        async fetchSeats(mid,cid,tid,did) {
            const _this=this
            Axios.get('http://localhost:8181/seatsMCTD',{
                params:{
                    mid: mid,
                    cid: cid,
                    tid: tid,
                    did: did
                }
            }).then(function(res){
                _this.seats = res.data;
            }).catch(function (error) {
                console.log(error);
            });

            // const res = await fetch(`api/seatsM${mid}C${cid}T${tid}D${did}`)

            // const data = await res.json()

            // return data
        },
        async fetchSeat(mid,cid,tid,did,sid) {
            Axios.get('http://localhost:8181/seatsMCTD',{
                params:{
                    mid: mid,
                    cid: cid,
                    tid: tid,
                    did: did,
                    sid: sid
                }
            }).then(function(res){
                return res.json()
            }).catch(function (error) {
                console.log(error);
            });

            // const res = await fetch(`api/seatsM${mid}C${cid}T${tid}D${did}/${sid}`)

            // const data = await res.json()

            // return data
        }
    },
    async created () {
        this.fetchMovie(this.$route.query.mid)
        this.fetchCinema(this.$route.query.cid)
        console.log(this.movie.movie_cn_name);
        this.fetchSeats(this.$route.query.mid,this.$route.query.cid,this.$route.query.tid,this.$route.query.did)
        this.fetchHall(this.$route.query.mid,this.$route.query.cid,this.$route.query.tid,this.$route.query.did)
    }
}
</script>

<style scoped>
    Seat{
        float: left
    }
</style>
