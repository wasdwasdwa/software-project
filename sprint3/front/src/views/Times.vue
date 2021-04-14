<template>
    <div>
        <nav id="navbar-example2" class="navbar navbar-light bg-light px-3 mb-3">
            <a @click="getMovies(cinema.id)" class="navbar-brand" >{{cinema.name}}</a>
            <ul class="nav nav-pills">
                <li class="nav-item">
                <a class="nav-link text-dark fs-4 position-absolute top-50 start-50 translate-middle" href="#">{{movie.name}}</a>
                </li>
            </ul>
        </nav>
        <div :v-if="movie.state" data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-offset="0" tabindex="0">   
            <p>
            <a class="btn btn-dark text-light" data-bs-toggle="collapse" href="#multiCollapseExample1" role="button" aria-expanded="false" aria-controls="multiCollapseExample1">{{date0}}</a>
            <button class="btn btn-dark text-light" type="button" data-bs-toggle="collapse" data-bs-target="#multiCollapseExample2" aria-expanded="false" aria-controls="multiCollapseExample2">{{date1}}</button>
            <button class="btn btn-dark text-light" type="button" data-bs-toggle="collapse" data-bs-target="#multiCollapseExample3" aria-expanded="false" aria-controls="multiCollapseExample3">{{date2}}</button>
            <button class="btn btn-dark text-light" type="button" data-bs-toggle="collapse" data-bs-target="#multiCollapseExample4" aria-expanded="false" aria-controls="multiCollapseExample4">{{date3}}</button>
            </p>
            <div class="row">
            <div class="col">
                <div class="collapse multi-collapse" id="multiCollapseExample1">
                <div class="card card-body">
                    <div class="col-sm-12 col-lg-12 col-md-12" v-for="time in timetable0" :key="time.id">
                    <Time :time="time" :cid="cid" :mid="mid"/>
                    </div>
                </div>
                </div>
            </div>
            </div>
            <div class="row">
            <div class="col">
                <div class="collapse multi-collapse" id="multiCollapseExample2">
                <div class="card card-body">
                    <div class="col-sm-12 col-lg-12 col-md-12" v-for="time in timetable1" :key="time.id">
                    <Time :time="time" :cid="cid" :mid="mid"/>
                    </div>
                </div>
                </div>
            </div>
            </div>
            <div class="row">
            <div class="col">
                <div class="collapse multi-collapse" id="multiCollapseExample3">
                <div class="row card card-body">
                    <div class="col-sm-12 col-lg-12 col-md-12" v-for="time in timetable2" :key="time.id">
                    <Time :time="time" :cid="cid" :mid="mid"/>
                    </div>
                </div>
                </div>
            </div>
            </div>
            <div class="row">
            <div class="col">
                <div class="collapse multi-collapse" id="multiCollapseExample4">
                <div class="row card card-body">
                    <div class="col-sm-12 col-lg-12 col-md-12" v-for="time in timetable3" :key="time.id">
                    <Time :time="time" :cid="cid" :mid="mid"/>
                    </div>
                </div>
                </div>
            </div>
            </div>
        </div>
        <div :v-else="!movie.state" data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-offset="0" tabindex="0">
        </div>
    </div>
</template>

<script>
import Time from '../components/Time'

export default {
    name: 'Times',
    components: {
        Time
    },
    data() {
        return {
            cinema: Object,
            movie: Object,
            timetable0: Array,
            timetable1: Array,
            timetable2: Array,
            timetable3: Array,
            date0: String,
            date1: String,
            date2: String,
            date3: String,
            mid: parseInt(this.$route.query.mid),
            cid: parseInt(this.$route.query.cid)
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
        async fetchCinema(cid) {
            const res = await fetch(`api/cinemas/${cid}`)
            // const res = await fetch('api/cinemas',{
            //     method: 'GET',
            //     headers: {
            //         'Content-type': 'application/json',
            //     },
            //     body: JSON.stringify(cid)
            // })

            const data = await res.json()

            return data
        },
        async fetchMovie(mid) {
            const res = await fetch(`api/movies/${mid}`)
            // const res = await fetch('api/movies',{
            //     method: 'GET',
            //     headers: {
            //         'Content-type': 'application/json',
            //     },
            //     body: JSON.stringify(cid)
            // })

            const data = await res.json()

            return data
        },
        async fetchTimetables(mid,cid,did) {
            const res = await fetch(`api/movie${mid}Cinema${cid}Time${did}`)
            // const res = await fetch('api/movies',{
            //     method: 'GET',
            //     headers: {
            //         'Content-type': 'application/json',
            //     },
            //     body: JSON.stringify(cid)
            // })

            const data = await res.json()

            return data
        },
        getdate() {
            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
		
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            var currentdate = year + "." + month + "." + strDate;
            return currentdate;
        },
        getBeforeDate(num) {
            let n = num;
            let d = new Date();
            let year = d.getFullYear();
            let mon = d.getMonth() + 1;
            let day = d.getDate();
            if(day <= n) {
                if(mon > 1) {
                    mon = mon - 1;
                } else {
                    year = year - 1;
                    mon = 12;
                }
            }
            d.setDate(d.getDate() - n);
            year = d.getFullYear();
            mon = d.getMonth() + 1;
            day = d.getDate();
            let s = year + "." + (mon < 10 ? ('0' + mon) : mon) + "." + (day < 10 ? ('0' + day) : day);
            return s;
        }
    },
    async created() {
        this.cinema = await this.fetchCinema(this.$route.query.cid)
        this.movie = await this.fetchMovie(this.$route.query.mid)
        this.timetable0 = await this.fetchTimetables(this.$route.query.mid,this.$route.query.cid,0)
        this.timetable1 = await this.fetchTimetables(this.$route.query.mid,this.$route.query.cid,1)
        this.timetable2 = await this.fetchTimetables(this.$route.query.mid,this.$route.query.cid,2)
        this.timetable3 = await this.fetchTimetables(this.$route.query.mid,this.$route.query.cid,3)
        this.date0 = this.getBeforeDate(0)
        this.date1 = this.getBeforeDate(-1)
        this.date2 = this.getBeforeDate(-2)
        this.date3 = this.getBeforeDate(-3)
    }
}
</script>
