<template>
  <transition name="el-zoom-in-top">
    <div
      class="el-table-cn.commodityManagement.filter"
      v-if="multiple"
      v-clickoutside="handleOutsideClick"
      v-show="showPopper">
      <div class="el-table-filter__content">
        <el-scrollbar wrap-class="el-table-filter__wrap">
          <el-checkbox-group class="el-table-filter__checkbox-group" v-model="filteredValue">
            <el-checkbox
              v-for="cn.commodityManagement.filter in filters"
              :key="cn.commodityManagement.filter.value"
              :label="cn.commodityManagement.filter.value">{{ cn.commodityManagement.filter.text }}</el-checkbox>
          </el-checkbox-group>
        </el-scrollbar>
      </div>
      <div class="el-table-filter__bottom">
        <button @click="handleConfirm"
          :class="{ 'is-disabled': filteredValue.length === 0 }"
          :disabled="filteredValue.length === 0">{{ t('el.table.confirmFilter') }}</button>
        <button @click="handleReset">{{ t('el.table.resetFilter') }}</button>
      </div>
    </div>
    <div
      class="el-table-cn.commodityManagement.filter"
      v-else
      v-clickoutside="handleOutsideClick"
      v-show="showPopper">
      <ul class="el-table-filter__list">
        <li class="el-table-filter__list-item"
            :class="{ 'is-active': filterValue === undefined || filterValue === null }"
            @click="handleSelect(null)">{{ t('el.table.clearFilter') }}</li>
        <li class="el-table-filter__list-item"
            v-for="cn.commodityManagement.filter in filters"
            :label="cn.commodityManagement.filter.value"
            :key="cn.commodityManagement.filter.value"
            :class="{ 'is-active': isActive(cn.commodityManagement.filter) }"
            @click="handleSelect(cn.commodityManagement.filter.value)" >{{ cn.commodityManagement.filter.text }}</li>
      </ul>
    </div>
  </transition>
</template>

<script type="text/babel">
  import Popper from 'element-ui/src/utils/vue-popper';
  import { PopupManager } from 'element-ui/src/utils/popup';
  import Locale from 'element-ui/src/mixins/locale';
  import Clickoutside from 'element-ui/src/utils/clickoutside';
  import Dropdown from './dropdown';
  import ElCheckbox from 'element-ui/packages/checkbox';
  import ElCheckboxGroup from 'element-ui/packages/checkbox-group';
  import ElScrollbar from 'element-ui/packages/scrollbar';

  export default {
    name: 'ElTableFilterPanel',

    mixins: [Popper, Locale],

    directives: {
      Clickoutside
    },

    components: {
      ElCheckbox,
      ElCheckboxGroup,
      ElScrollbar
    },

    props: {
      placement: {
        type: String,
        default: 'bottom-end'
      }
    },

    methods: {
      isActive(cn.commodityManagement.filter) {
        return cn.commodityManagement.filter.value === this.filterValue;
      },

      handleOutsideClick() {
        setTimeout(() => {
          this.showPopper = false;
        }, 16);
      },

      handleConfirm() {
        this.confirmFilter(this.filteredValue);
        this.handleOutsideClick();
      },

      handleReset() {
        this.filteredValue = [];
        this.confirmFilter(this.filteredValue);
        this.handleOutsideClick();
      },

      handleSelect(filterValue) {
        this.filterValue = filterValue;

        if ((typeof filterValue !== 'undefined') && (filterValue !== null)) {
          this.confirmFilter(this.filteredValue);
        } else {
          this.confirmFilter([]);
        }

        this.handleOutsideClick();
      },

      confirmFilter(filteredValue) {
        this.table.store.commit('filterChange', {
          column: this.column,
          values: filteredValue
        });
        this.table.store.updateAllSelected();
      }
    },

    data() {
      return {
        table: null,
        cell: null,
        column: null
      };
    },

    computed: {
      filters() {
        return this.column && this.column.filters;
      },

      filterValue: {
        get() {
          return (this.column.filteredValue || [])[0];
        },
        set(value) {
          if (this.filteredValue) {
            if ((typeof value !== 'undefined') && (value !== null)) {
              this.filteredValue.splice(0, 1, value);
            } else {
              this.filteredValue.splice(0, 1);
            }
          }
        }
      },

      filteredValue: {
        get() {
          if (this.column) {
            return this.column.filteredValue || [];
          }
          return [];
        },
        set(value) {
          if (this.column) {
            this.column.filteredValue = value;
          }
        }
      },

      multiple() {
        if (this.column) {
          return this.column.filterMultiple;
        }
        return true;
      }
    },

    mounted() {
      this.popperElm = this.$el;
      this.referenceElm = this.cell;
      this.table.bodyWrapper.addEventListener('scroll', () => {
        this.updatePopper();
      });

      this.$watch('showPopper', (value) => {
        if (this.column) this.column.filterOpened = value;
        if (value) {
          Dropdown.open(this);
        } else {
          Dropdown.close(this);
        }
      });
    },
    watch: {
      showPopper(val) {
        if (val === true && parseInt(this.popperJS._popper.style.zIndex, 10) < PopupManager.zIndex) {
          this.popperJS._popper.style.zIndex = PopupManager.nextZIndex();
        }
      }
    }
  };
</script>
