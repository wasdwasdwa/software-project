<template>
        <div @click="selected(seat.id)" :class="[seat.isAvailable ? 'available' : 'unavailable',!seat.isSelected ? 'available' : 'unavailable',tempSelect ? 'selected' :'', 'seat']" :style="style">
        <!-- <span class="display-7 font-weight-thin">{{seat.row}}-{{seat.column}}</span> -->
    </div>
</template>

<script>
export default {
    name: 'Seat',
    props: {
        seat: Object,
    },
    data() {
        return {
            row: 100*(this.seat.row-1),
            column: 100*(this.seat.column-1),
            tempSelect: false
        }
    },
    watch: {
        seat:{
            handler(val, oldVal){
                if(val.isSelected != oldVal.isSelected)
                    this.count()
            },
            deep:true
        }
    },
    computed: {
        style() {
            return 'left:' + this.column + ";top:" +this.row;
        }
    },
    methods: {
        selected(id) {
            if(this.seat.isAvailable) {
                this.tempSelect = !this.tempSelect
                this.$emit('seatSelected',id);
            }
        },
        count() {
            if(this.tempSelect == true && !this.seat.isSelected) {
                this.tempSelect = false
            }
        },
    }
}
</script>


<style>
    .seat {
    height: 67px;
    width: 79px;
    margin: 2px 3px;
    font-size: 1rem;
    color: white;
    box-shadow: 0 2px 5px 2px rgba(darken(#f8c578, 40%),0.75);
    text-shadow: 1px 1px darken(#40756e, 40%);
    float: left;
    position: relative;
    text-align: center;
    padding-top:12px;
    }

    .available{
        background-image: url('../assets/icon/red.png');
        cursor: pointer;
        background-size: 100% 100%;
        background-repeat:no-repeat;
    }

    .available:hover:not(.selected) {
        background-image: url('../assets/icon/blue.png');
        background-size: 100% 100%;
        background-repeat:no-repeat;
    }


    .unavailable{
        background-image: url('../assets/icon/grey.png');
        background-size: 100% 100%;
        background-repeat:no-repeat;
    }

    .selected{
        background-image: url('../assets/icon/yellow.png');
        background-size: 100% 100%;
        background-repeat:no-repeat;
    }

    .unavailable:hover:not(.selected){
        background-image: url('../assets/icon/grey.png');
        background-size: 100% 100%;
        background-repeat:no-repeat;
    }
</style>