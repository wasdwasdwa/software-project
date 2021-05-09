<template>
    <div class="card border-dark mb-1">
        <div class="card-header text-dark bg-light fs-5">{{order.order_id}}
        </div>
        <div class="card-body text-dark">
            <h5><b>Movie:</b> {{movie.movie_cn_name}}</h5>
            <h5><b>Cinema:</b> {{cinema.cinema_name}}</h5>
            <h5><b>Time:</b> {{schedule.newTime}}</h5>
            <h5><b>Hall:</b> {{hall.hall_name}}</h5>
            <h5><b>Seats:</b>{{order.order_position}}</h5>
<!--            <div v-for="seat in order.seats" :key="seat.id">-->
<!--                {{seat.row}}-{{seat.column}}-->
<!--            </div>-->
        </div>
    </div>
</template>

<script>
import Axios from "axios";

export default {
    name: 'myorder',
    props: {
        order: Object,
    },
    data() {
      return {
        movie: Object,
        cinema:Object,
        schedule:Object,
        hall:Object
      }
    },
    methods: {
      async fetchMovie(mid) {
        const _this=this
        await Axios.get('http://localhost:8181/movies/schedule_id',{
          params:{
            sid: mid
          }
        }).then(function(res){
          _this.movie = res.data;
        }).catch(function (error) {
          console.log(error);
        });
      },
      async fetchCinema(mid) {
        const _this=this
        console.log(123321)
        await Axios.get('http://localhost:8181/cinemas/schedule_id',{
          params:{
            sid: mid
          }
        }).then(function(res){
          console.log(res.data)
          _this.cinema = res.data;
        }).catch(function (error) {
          console.log(error);
        });
      },
      async fetchHail(mid) {
        const _this=this
        await Axios.get('http://localhost:8181/hall/schedule_id',{
          params:{
            tid: mid
          }
        }).then(function(res){
          console.log(res)
          _this.hall = res.data["hall"];
        }).catch(function (error) {
          console.log(error);
        });
      },
      async fetchSchedule(mid) {
        const _this=this
        await Axios.get('http://localhost:8181/schedule/schedule_id',{
          params:{
            sid: mid
          }
        }).then(function(res){
          console.log(res.data)
          _this.schedule = res.data;
        }).catch(function (error) {
          console.log(error);
        });
      },
      hello(sid){
        const _this = this;
        console.log(sid);
        Axios.get('http://localhost:8181/movies/schedule_id',{
          params:{
            sid: sid
          }
        }).then(function(res){
          _this.movie = res.data
        }).catch(function (error) {
          console.log(error);
        });
      }
    },
    async created (){
        this.fetchMovie(this.order.schedule_id)
        this.fetchCinema(this.order.schedule_id)
        this.fetchSchedule(this.order.schedule_id)
        this.fetchHail(this.order.schedule_id)
    }
}
</script>
