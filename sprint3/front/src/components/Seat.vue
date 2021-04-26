<template>
        <div @click="selected(seat.id)" :class="[seat.isAvailable ? 'available' : 'unavailable',!seat.isSelected ? 'available' : 'unavailable',tempSelect ? 'selected' :'', 'seat']" :style="style">
        <span class="display-7 font-weight-thin">{{seat.row}}-{{seat.column}}</span>
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
            row: 50*(this.seat.row-1),
            column: 60*(this.seat.column-1),
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
    height: 50px;
    width: 60px;
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
        background-color: rgba(133,36,12);
        cursor: pointer;
    }

    .available:hover:not(.selected) {
        background-color: #40756e;
    }


    .unavailable{
        background-color:#999999;
    }

    .selected{
        background-color: rgba(237,169,58 ,0.9);
    }

    .unavailable:hover:not(.selected){
        background-color:#999999;
    }
</style>