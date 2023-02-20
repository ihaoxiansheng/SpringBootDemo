<template>
  <div>
    <!--    当formstyle为3的时候，显示为文字-->
    <div v-if="formStyle == 3">{{ selectSystemName.join(',') }}</div>
    <div v-else>
      <el-form-item>
        <el-checkbox-group v-model="selectMainSystems">
          <el-checkbox
            v-for="mainSystem in mainSystems"
            :key="mainSystem"
            :label="mainSystem"
          >
            {{ mainSystem }}
          </el-checkbox>

        </el-checkbox-group>
      </el-form-item>
      <el-form-item
        v-for="selectMainSystem in selectMainSystems"
        :key="selectMainSystem"
        :label="selectMainSystem"
      >
        <el-checkbox-group v-model="selectSystems">
          <el-checkbox
            v-for="system in systems[selectMainSystem]"
            :key="system.systemId"
            :label="system.systemId"
          >
            {{ system.name }}
          </el-checkbox>

        </el-checkbox-group>
        <span v-if="selectMainSystem == '合同法律'" class="notice">申请合同查询权限必须勾选合同台账，并由省公司及集团法律部门审批</span>
      </el-form-item>
      <el-form-item
        v-if="selectSystems.includes(2222)"
        label="合同系统-合同法律-合同台账"
      >
        <el-checkbox-group v-model="selectSystems">
          <el-checkbox
            v-for="system in systems['合同系统-合同法律-合同台账']"
            :key="system.systemId"
            :label="system.systemId"
          >
            {{ system.name }}
          </el-checkbox>
        </el-checkbox-group>
        <p class="notice-type2">请在工单申请内容详细描述具体查询范围（如XX公司XX部门F1类合同）、理由及期限（长期或X年X月X日-X年X月X日）</p>
      </el-form-item>
      <el-form-item
        v-if="selectSystems.includes(937) || selectSystems.includes(956) || selectSystems.includes(919) || selectSystems.includes(962) || selectSystems.includes(957) || selectMainSystems.includes('OA系统')"
        label="模板"
      >
        <el-button v-if="selectSystems.includes(937)" type="text" @click="downFile(1024)">中国电信集中MSS项目_合并报表_用户及角色权限模板.xlsx</el-button>
        <el-button v-if="selectSystems.includes(956)" type="text" @click="downFile(1025)">中国电信集中MSS项目_关联交易_用户及角色权限模板.xlsx</el-button>
        <el-button v-if="selectSystems.includes(919)" type="text" @click="downFile(1022)">中国电信集中MSS项目-数据分析平台-用户及角色权限模板.xlsx</el-button>
        <el-button v-if="selectSystems.includes(962)" type="text" @click="downFile(1027)">权限清单模板_企业价值视图_数据集市.xlsx</el-button>
        <el-button v-if="selectSystems.includes(957)" type="text" @click="downFile(1023)">中国电信集中MSS项目_集中报表平台_用户及角色权限模板_省份(新)_20180620.xlsx</el-button>
        <el-button v-if="selectMainSystems.includes('OA系统')" type="text" @click="downFile(1026)">中国电信集团OA云会议权限申请表_v1.0.xlsx</el-button>
      </el-form-item>
    </div>
  </div>
</template>

<script>
export default {
  model: {
    prop: 'value',
    event: 'selectChange'
  },
  props: {
    value: {
      type: Array,
      default: () => {
        return []
      }
    }
  },
  data() {
    return {
      mainSystems: [],
      systems: [],
      selectMainSystems: [],
      selectSystems: [],
      systemRelations: {},
      specSystemRelations: [],
      formStyle: 0,
      systemOptions: [],
      selectSystemName: []

    }
  },
  computed: {},
  watch: {
    value(val) {
      if (sessionStorage.getItem('formStyle') == 2) {
        val.forEach(e => {
          for (const i in this.systemRelations) {
            if (
              this.systemRelations[i].includes(e) &&
              !this.selectMainSystems.includes(i)
            ) {
              this.selectMainSystems.push(i)
            }
          }
        })
      }
      if (sessionStorage.getItem('formStyle') == 3) {
        this.$get(
          window.SITE_CONFIG['orderHandleCenter'] +
        '/MSSPermissionApplicationApi/queryMssSystem'
        ).then(res => {
          for (const i in res) {
            this.systemOptions = [...this.systemOptions, ...res[i]]
          }
          val.forEach(e => {
            this.systemOptions.forEach(m => {
              if (m.systemId == e) {
                this.selectSystemName.push(m.name)
              }
            })
          })
        })
      }

      // console.log(this.selectSystemName)
      this.selectSystems = val
    },
    selectMainSystems(newVal, oldVal) {
      if (oldVal.length > newVal.length) {
        const deletVal = oldVal.filter((item) => !newVal.includes(item))[0]
        if (deletVal == '合同法律') {
          let arr = this.systems[deletVal].map((e) => {
            return e.systemId
          })

          arr = [...arr, ...this.specSystemRelations]
          const copySystems = JSON.parse(JSON.stringify(this.selectSystems))
          const deleteItems = []
          copySystems.forEach((e) => {
            if (arr.includes(e)) {
              deleteItems.push(e)
            }
          })
          this.selectSystems = copySystems.filter((item) => !deleteItems.includes(item))
        } else {
          const arr = this.systems[deletVal].map((e) => {
            return e.systemId
          })
          const copySystems = JSON.parse(JSON.stringify(this.selectSystems))
          const deleteItems = []
          copySystems.forEach((e) => {
            if (arr.includes(e)) {
              deleteItems.push(e)
            }
          })
          this.selectSystems = copySystems.filter((item) => !deleteItems.includes(item))
        }
      }
      console.log(newVal)
    },
    selectSystems(newVal, oldVal) {
      const specVal = [2223, 2224, 2225, 2226]
      const addVal = newVal.filter((item) => !oldVal.includes(item))[0]
      if (specVal.includes(addVal)) {
        oldVal.forEach(e => {
          if (specVal.includes(e)) {
            newVal.splice(newVal.indexOf(e), 1)
          }
        })
      }
      this.$emit('selectChange', newVal)
      console.log(newVal)
    }
  },
  mounted() {
    this.getSystem()
  },
  methods: {
    getSystem() {
      this.formStyle = sessionStorage.getItem('formStyle')
      this.$get(
        window.SITE_CONFIG['orderHandleCenter'] +
        '/MSSPermissionApplicationApi/queryMssSystem'
      ).then(res => {
        this.systems = res
        this.mainSystems = Object.keys(res).filter(e => {
          return e != '合同系统-合同法律-合同台账'
        })
        console.log(this.mainSystems)
        for (const i in res) {
          const arr = []
          res[i].forEach(e => {
            arr.push(e.systemId)
          })
          if (i !== '合同系统-合同法律-合同台账') {
            this.systemRelations[i] = arr
          } else {
            this.specSystemRelations = arr
          }
        }
        console.log(this.systemRelations)
        console.log(this.specSystemRelations)
      })
    },
    downFile(fileId) {
      window.location.href = window.SITE_CONFIG['fileCenterUrl'] + '/gcFile/downloadFile/' + fileId +
        '?token=' + this.$gc.getToken()
    }

  }
}
</script>

<style scoped>
.notice {
  color:red;
  margin-left: 20px;
}
.notice-type2 {
  color: red;
}

</style>
