<template>
    <div class="card border-dark mb-1">
        <div class="card-header text-dark bg-light fs-5">{{time.newTime}}   
        </div>
        <div class="card-body text-dark">
            <p class="card-text text-dark fs-6">Hall No.{{time.hall_id}}</p>
            <a @click="getSeats(mid,cid,time.schedule_id,tid)" class="btn btn-dark text-white mt-2">
            <svg  xmlns="http://www.w3.org/2000/svg"  width="16" height="16" fill="currentColor" class="bi bi-cart3" viewBox="0 0 16 16">
            <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </svg></a>  
        </div>
    </div>
</template>

<script>
export default {
    name: 'Time',
    props: {
        time: Object,
        mid: {
            type: Number,
            default: 0
        },
        cid: {
            type: Number,
            default: 0
        },
        tid: Number,
    },
    methods: {
        getSeats(mid,cid,did,tid) {
            this.$router.push({
                name: 'Seats',
                query: {
                    mid: mid,
                    cid: cid,
                    did: did,
                    tid: tid,
                }
            })        
        }
    }
}
</script>
