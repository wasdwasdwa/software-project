<template>
    <div class="seat" @click="addToOrder(seat)"
        :class="{'available': seat.isAvailable,
        'unavailable': !seat.isAvailable,
        'temp-unavailable': seat.isTempUnavailable,
        'selected': seat.isSelected}" >
        <span class="display-1 font-weight-thin">{{seat.row}} {{seat.column}}</span>
    </div>
</template>

<script>
export default {
    name: 'Seat',
    props: {
        seat: Object,
    },
    methods: {
        addToOrder() {

        }
    }
}
</script>


<style scoped>
    .seat {
    height: 100%;
    margin: 0 10px;
    font-size: 3rem;
    color: white;
    box-shadow: 0 2px 5px 2px rgba(darken(#666666, 40%),0.75);
    text-shadow: 1px 1px darken(#333333, 40%);
    }

    .available{
        background-color: rgba(#FFCC33,0.9);
        cursor: pointer;
    }

    .available:hover:not(.selected) {
        background-color: whitesmoke;
        color: black;
    }

    .selected{
        background-color: darken(#666666, 40%);
    }

    .temp-unavailable{
        background: repeating-linear-gradient(
        45deg,
            #9a0000, #9a0000 10px, #FFCC33 10px, #FFCC33 20px
        );
    }

    .unavailable{
        background-color: #9a0000;
    }
</style>