<template>
<div>
    <button @click="count()" type="button" class="btn btn-dark text-white mt-2" data-bs-toggle="modal" data-bs-target="#exampleModal" style="width: 100%;">
        Confirm!
    </button>

    <div class="modal fade" id="exampleModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-centered">
            <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="exampleModalLabel">Order!</h3>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h5><b>Movie:</b> {{movie.movie_cn_name}}</h5>
                <h5><b>Cinema:</b> {{cinema.cinema_name}}</h5>
                <h5><b>Time:</b> {{schedule.newTime}}</h5>
                <h5><b>Hall:</b> {{hall.hall_name}}</h5>
                <h5><b>Seats:</b></h5> 
                <div v-for="seat in selected" :key="seat.id">
                row:{{seat.row}}-column:{{seat.column}}
                </div>
            </div>
            <div class="modal-footer">
                <button @click="cancel()" type="button" id="cancel" class="btn btn-secondary" data-bs-dismiss="modal">Cancel in <span id="count"></span> s</button>
                <button @click="order()" type="button" class="btn btn-dark text-light">Buy Now!</button>
            </div>
            </div>
        </div>
    </div>
</div> 
</template>

<script>
export default {
    name: "Order",
    props: {
        movie: Object,
        hall: Object,
        cinema: Object,
        schedule:Object,
        selected: Array,
    },
    data() {
        return{
            isCanceled: {
                type: Boolean,
                default: false
            },
            time: []
        }
    },
    methods: {
        order() {
            this.$emit('order');
            this.$router.push({
              name:"MyOrder",
            })
        },
        cancel() {
            let leng = this.time.length;
            let time = this.time
            for(let i = 0;i < leng;i++) {
                clearTimeout(time[i])
            }
            this.time = []
            this.$emit('unselect');
        },
        count() {
            // const _self = this
            let end;
            const el = document.querySelector('#count')
            function countdown() {
                const now = +new Date()
                const diff = end - now 
                let count = Math.round(diff / 1000)
                if (count < 0) return
                el.innerText = count
                if(count == 0) {
                    document.getElementById('cancel').click();
                    // _self.$emit('unselect');
                    return;
                }
                let delay = ((end - count * 1000) - now + 1000) % 1000
                if (delay == 0) delay = 1000
                let temp = setTimeout(() => {
                    countdown()
                }, delay)
                self.time = [...self.time,temp]
            }
            end = +new Date() + 6000
            countdown()
        }
    }
}
</script>
