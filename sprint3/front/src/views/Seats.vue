<template>
    <div>
        <nav id="navbar-example2" class="navbar navbar-light bg-light px-3 mb-3">
            <a class="navbar-brand" >{{movie.movie_cn_name}}</a>
        </nav>
        <div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-offset="0" tabindex="0">
            <div class="row">
                <div class="col-sm-5 col-lg-5 col-md-5">
                    <img :src="movie.movie_picture" class="d-block w-100" :alt="movie.movie_cn_name">
                </div>
                <div class="col-sm-7 col-lg-7 col-md-7">
                    <h4>Brief description:</h4>
                    <p>{{movie.movie_detail}}</p>
                    <h4>Movie director:</h4>
                    <p>{{movie.movie_director}}</p>
                    <h4>Main actors:</h4>
                    <p>{{movie.movie_actor}}</p>
                    <h4>Time:</h4>
                    <p>{{schedule.newTime}}</p>
                    <h4>Hall:</h4>
                    <p>{{hall.hall_name}}</p>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col-sm-8 col-lg-8 col-md-8">
                    <div v-for="seat in seats" :key="seat.id">
                    <Seat @seatSelected="select($event)" :seat="seat" :mid="mid" :cid="cid" :did="did"/>
                    </div>
                </div>
                <Order @order="getOrder()" @unselect="unselect()" v-if="selected.length!=0" :movie="movie" :hall="hall" :cinema="cinema" :schedule="schedule" :selected="selected"/>
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
            schedule:Object,
            selected: []
        }
    },
    methods: {
        async getOrder() {
            let temp = this.selected
            let py = temp.length
            let _this = this
            for(let i = 0; i < py;i++) {
                let id = temp[i].id
                const seatOrdered = await this.fetchSeat(this.mid,this.cid,this.tid,this.did,id)
                const upDate = {...seatOrdered,isAvailable: !seatOrdered.isAvailable}
                let data

                await Axios.get('http://localhost:8181/order/updateorder', {
                    params:{ 
                        mid: this.mid,
                        cid: this.cid,
                        tid: _this.schedule.schedule_id,
                        did: this.did,
                        sid: id
                    },
                    upDate
                }).then(function(res){
                    data = res.data
                }).catch(function(error){
                    console.log(error)
                });

                this.seats = this.seats.map((seat)=>seat.id === id ? {...seat,isAvailable:data.isAvailable}:seat
                )
            }
        },
        async select(msg) {
            const _this = this;
            let id = msg;
            const seatSelected = await this.fetchSeat(this.mid,this.cid,this.tid,this.did,id)
            const upDate = {...seatSelected,isSelected: !seatSelected.isSelected}

            let data;
            if(upDate.isSelected) {
                this.selected = [...this.selected,upDate]
            } else {
                this.selected = this.selected.filter((seat) => seat.id !== upDate.id)
            }
            await Axios.get('http://localhost:8181/seats/changeSelected', {
                params:{
                    sid : id
                },
                upDate
            }).then(function(res){
                data = res.data
              console.log(data)
            }).catch(function(error){
                console.log(error)
            });

            _this.seats = _this.seats.map((seat)=>seat.id === id ? {...seat,isSelected:data.isSelected}:seat
          )
        },
        async unselect() {
            let py = this.selected.length
            let temp = this.selected
            for(let i = 0; i < py;i++) {
                let id = temp[i].id
                const seatUnselect = await this.fetchSeat(this.mid,this.cid,this.tid,this.did,id)
                const upDate = {...seatUnselect,isSelected: !seatUnselect.isSelected}

                let data

                await Axios.get('http://localhost:8181/seats/changeSelected', {
                    params:{
                        sid: id
                    },
                    upDate
                }).then(function(res){
                    data = res.data
                }).catch(function(error){
                    console.log(error)
                });

                this.seats = this.seats.map((seat)=>seat.id === id ? {...seat,isSelected:data.isSelected}:seat
                )
                this.selected = this.selected.filter((seat) => seat.id !== id)
              console.log(i)
            }
        },
        async fetchMovie(mid) {
            const _this=this
            Axios.get('http://localhost:8181/movies/movie_id',{
                params:{
                    mid: mid
                }
            }).then(function(res){
                _this.movie = res.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        async fetchCinema(cid) {
            const _this=this
            Axios.get('http://localhost:8181/cinemas/cinema_id',{
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
        async fetchHall(tid) {
          console.log(tid);
            const _this=this;
            Axios.get('http://localhost:8181/hall/schedule_id',{
                params:{
                    tid:tid
                }
            }).then(function(res){
              console.log(res.data);
                _this.hall = res.data["hall"];
                _this.schedule = res.data["schedule"];
            }).catch(function (error) {
                console.log(error);
            });

            // const res = await fetch(`api/movie${mid}Cinema${cid}Time${tid}/${did}`)

            // const data = await res.json()

            // return data
        },
        async fetchSeats(mid,cid,tid,did) {
            const _this=this
            Axios.get('http://localhost:8181/seats',{
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
            const res = await Axios.get('http://localhost:8181/seats/seat_id', {
              params: {
                mid: mid,
                cid: cid,
                tid: tid,
                did: did,
                sid: sid
              }
            })


          const data = await res.data
          return data
        }
    },
    async created () {
        this.fetchMovie(this.$route.query.mid)
        this.fetchCinema(this.$route.query.cid)
        console.log(this.$route.query.did)
        this.fetchSeats(this.$route.query.mid,this.$route.query.cid,this.$route.query.tid,this.$route.query.did)
        this.fetchHall(this.$route.query.did)
    }
}
</script>

<style scoped>
    Seat{
        float: left
    }
</style>
