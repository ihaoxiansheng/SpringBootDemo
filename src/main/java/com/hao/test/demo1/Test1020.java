package com.hao.test.demo1;

/**
 * @author xu.liang
 * @since 2022/10/20 15:52
 */
public class Test1020 {
    public static void main(String[] args) {

        String str = "<template>\n" +
                "  <div>\n" +
                "    <!--    当formstyle为3的时候，显示为文字-->\n" +
                "    <div v-if=\"formStyle == 3\">{{ selectSystemName.join(',') }}</div>\n" +
                "    <div v-else>\n" +
                "      <el-form-item>\n" +
                "        <el-checkbox-group v-model=\"selectMainSystems\">\n" +
                "          <el-checkbox\n" +
                "            v-for=\"mainSystem in mainSystems\"\n" +
                "            :key=\"mainSystem\"\n" +
                "            :label=\"mainSystem\"\n" +
                "          >\n" +
                "            {{ mainSystem }}\n" +
                "          </el-checkbox>\n" +
                "\n" +
                "        </el-checkbox-group>\n" +
                "      </el-form-item>\n" +
                "      <el-form-item\n" +
                "        v-for=\"selectMainSystem in selectMainSystems\"\n" +
                "        :key=\"selectMainSystem\"\n" +
                "        :label=\"selectMainSystem\"\n" +
                "      >\n" +
                "        <el-checkbox-group v-model=\"selectSystems\">\n" +
                "          <el-checkbox\n" +
                "            v-for=\"system in systems[selectMainSystem]\"\n" +
                "            :key=\"system.systemId\"\n" +
                "            :label=\"system.systemId\"\n" +
                "          >\n" +
                "            {{ system.name }}\n" +
                "          </el-checkbox>\n" +
                "\n" +
                "        </el-checkbox-group>\n" +
                "        <span v-if=\"selectMainSystem == '合同法律'\" class=\"notice\">申请合同查询权限必须勾选合同台账，并由省公司及集团法律部门审批</span>\n" +
                "      </el-form-item>\n" +
                "      <el-form-item\n" +
                "        v-if=\"selectSystems.includes(2222)\"\n" +
                "        label=\"合同系统-合同法律-合同台账\"\n" +
                "      >\n" +
                "        <el-checkbox-group v-model=\"selectSystems\">\n" +
                "          <el-checkbox\n" +
                "            v-for=\"system in systems['合同系统-合同法律-合同台账']\"\n" +
                "            :key=\"system.systemId\"\n" +
                "            :label=\"system.systemId\"\n" +
                "          >\n" +
                "            {{ system.name }}\n" +
                "          </el-checkbox>\n" +
                "        </el-checkbox-group>\n" +
                "        <p class=\"notice-type2\">请在工单申请内容详细描述具体查询范围（如XX公司XX部门F1类合同）、理由及期限（长期或X年X月X日-X年X月X日）</p>\n" +
                "      </el-form-item>\n" +
                "      <el-form-item\n" +
                "        v-if=\"selectSystems.includes(937) || selectSystems.includes(956) || selectSystems.includes(919) || selectSystems.includes(962) || selectSystems.includes(957) || selectMainSystems.includes('OA系统')\"\n" +
                "        label=\"模板\"\n" +
                "      >\n" +
                "        <el-button v-if=\"selectSystems.includes(937)\" type=\"text\" @click=\"downFile(1024)\">中国电信集中MSS项目_合并报表_用户及角色权限模板.xlsx</el-button>\n" +
                "        <el-button v-if=\"selectSystems.includes(956)\" type=\"text\" @click=\"downFile(1025)\">中国电信集中MSS项目_关联交易_用户及角色权限模板.xlsx</el-button>\n" +
                "        <el-button v-if=\"selectSystems.includes(919)\" type=\"text\" @click=\"downFile(1022)\">中国电信集中MSS项目-数据分析平台-用户及角色权限模板.xlsx</el-button>\n" +
                "        <el-button v-if=\"selectSystems.includes(962)\" type=\"text\" @click=\"downFile(1027)\">权限清单模板_企业价值视图_数据集市.xlsx</el-button>\n" +
                "        <el-button v-if=\"selectSystems.includes(957)\" type=\"text\" @click=\"downFile(1023)\">中国电信集中MSS项目_集中报表平台_用户及角色权限模板_省份(新)_20180620.xlsx</el-button>\n" +
                "        <el-button v-if=\"selectMainSystems.includes('OA系统')\" type=\"text\" @click=\"downFile(1026)\">中国电信集团OA云会议权限申请表_v1.0.xlsx</el-button>\n" +
                "      </el-form-item>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</template>\n" +
                "\n" +
                "<script>\n" +
                "export default {\n" +
                "  model: {\n" +
                "    prop: 'value',\n" +
                "    event: 'selectChange'\n" +
                "  },\n" +
                "  props: {\n" +
                "    value: {\n" +
                "      type: Array,\n" +
                "      default: () => {\n" +
                "        return []\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  data() {\n" +
                "    return {\n" +
                "      mainSystems: [],\n" +
                "      systems: [],\n" +
                "      selectMainSystems: [],\n" +
                "      selectSystems: [],\n" +
                "      systemRelations: {},\n" +
                "      specSystemRelations: [],\n" +
                "      formStyle: 0,\n" +
                "      systemOptions: [],\n" +
                "      selectSystemName: []\n" +
                "\n" +
                "    }\n" +
                "  },\n" +
                "  computed: {},\n" +
                "  watch: {\n" +
                "    value(val) {\n" +
                "      if (sessionStorage.getItem('formStyle') == 2) {\n" +
                "        val.forEach(e => {\n" +
                "          for (const i in this.systemRelations) {\n" +
                "            if (\n" +
                "              this.systemRelations[i].includes(e) &&\n" +
                "              !this.selectMainSystems.includes(i)\n" +
                "            ) {\n" +
                "              this.selectMainSystems.push(i)\n" +
                "            }\n" +
                "          }\n" +
                "        })\n" +
                "      }\n" +
                "      if (sessionStorage.getItem('formStyle') == 3) {\n" +
                "        this.$get(\n" +
                "          window.SITE_CONFIG['orderHandleCenter'] +\n" +
                "        '/MSSPermissionApplicationApi/queryMssSystem'\n" +
                "        ).then(res => {\n" +
                "          for (const i in res) {\n" +
                "            this.systemOptions = [...this.systemOptions, ...res[i]]\n" +
                "          }\n" +
                "          val.forEach(e => {\n" +
                "            this.systemOptions.forEach(m => {\n" +
                "              if (m.systemId == e) {\n" +
                "                this.selectSystemName.push(m.name)\n" +
                "              }\n" +
                "            })\n" +
                "          })\n" +
                "        })\n" +
                "      }\n" +
                "\n" +
                "      // console.log(this.selectSystemName)\n" +
                "      this.selectSystems = val\n" +
                "    },\n" +
                "    selectMainSystems(newVal, oldVal) {\n" +
                "      if (oldVal.length > newVal.length) {\n" +
                "        const deletVal = oldVal.filter((item) => !newVal.includes(item))[0]\n" +
                "        if (deletVal == '合同法律') {\n" +
                "          let arr = this.systems[deletVal].map((e) => {\n" +
                "            return e.systemId\n" +
                "          })\n" +
                "\n" +
                "          arr = [...arr, ...this.specSystemRelations]\n" +
                "          const copySystems = JSON.parse(JSON.stringify(this.selectSystems))\n" +
                "          const deleteItems = []\n" +
                "          copySystems.forEach((e) => {\n" +
                "            if (arr.includes(e)) {\n" +
                "              deleteItems.push(e)\n" +
                "            }\n" +
                "          })\n" +
                "          this.selectSystems = copySystems.filter((item) => !deleteItems.includes(item))\n" +
                "        } else {\n" +
                "          const arr = this.systems[deletVal].map((e) => {\n" +
                "            return e.systemId\n" +
                "          })\n" +
                "          const copySystems = JSON.parse(JSON.stringify(this.selectSystems))\n" +
                "          const deleteItems = []\n" +
                "          copySystems.forEach((e) => {\n" +
                "            if (arr.includes(e)) {\n" +
                "              deleteItems.push(e)\n" +
                "            }\n" +
                "          })\n" +
                "          this.selectSystems = copySystems.filter((item) => !deleteItems.includes(item))\n" +
                "        }\n" +
                "      }\n" +
                "      console.log(newVal)\n" +
                "    },\n" +
                "    selectSystems(newVal, oldVal) {\n" +
                "      const specVal = [2223, 2224, 2225, 2226]\n" +
                "      const addVal = newVal.filter((item) => !oldVal.includes(item))[0]\n" +
                "      if (specVal.includes(addVal)) {\n" +
                "        oldVal.forEach(e => {\n" +
                "          if (specVal.includes(e)) {\n" +
                "            newVal.splice(newVal.indexOf(e), 1)\n" +
                "          }\n" +
                "        })\n" +
                "      }\n" +
                "      this.$emit('selectChange', newVal)\n" +
                "      console.log(newVal)\n" +
                "    }\n" +
                "  },\n" +
                "  mounted() {\n" +
                "    this.getSystem()\n" +
                "  },\n" +
                "  methods: {\n" +
                "    getSystem() {\n" +
                "      this.formStyle = sessionStorage.getItem('formStyle')\n" +
                "      this.$get(\n" +
                "        window.SITE_CONFIG['orderHandleCenter'] +\n" +
                "        '/MSSPermissionApplicationApi/queryMssSystem'\n" +
                "      ).then(res => {\n" +
                "        this.systems = res\n" +
                "        this.mainSystems = Object.keys(res).filter(e => {\n" +
                "          return e != '合同系统-合同法律-合同台账'\n" +
                "        })\n" +
                "        console.log(this.mainSystems)\n" +
                "        for (const i in res) {\n" +
                "          const arr = []\n" +
                "          res[i].forEach(e => {\n" +
                "            arr.push(e.systemId)\n" +
                "          })\n" +
                "          if (i !== '合同系统-合同法律-合同台账') {\n" +
                "            this.systemRelations[i] = arr\n" +
                "          } else {\n" +
                "            this.specSystemRelations = arr\n" +
                "          }\n" +
                "        }\n" +
                "        console.log(this.systemRelations)\n" +
                "        console.log(this.specSystemRelations)\n" +
                "      })\n" +
                "    },\n" +
                "    downFile(fileId) {\n" +
                "      window.location.href = window.SITE_CONFIG['fileCenterUrl'] + '/gcFile/downloadFile/' + fileId +\n" +
                "        '?token=' + this.$gc.getToken()\n" +
                "    }\n" +
                "\n" +
                "  }\n" +
                "}\n" +
                "</script>\n" +
                "\n" +
                "<style scoped>\n" +
                ".notice {\n" +
                "  color:red;\n" +
                "  margin-left: 20px;\n" +
                "}\n" +
                ".notice-type2 {\n" +
                "  color: red;\n" +
                "}\n" +
                "\n" +
                "</style>\n";

        // boolean b = xx.startsWith("<template>", 1);
        // System.out.println("b = " + b);
        // int index1 = xx.indexOf(">", 1);
        // System.out.println("index1 = " + index1);
        // int index2 = xx.lastIndexOf("</template>", 1);
        // System.out.println("index2 = " + index2);
        // String substring = xx.substring(index1, index2);
        // System.out.println("substring = " + substring);

        int index = str.indexOf("<template>");
        System.out.println("index = " + index);

        String templateStr = str.substring(str.indexOf("<template>") + 10, str.indexOf("</template>"));
        String scriptStr = str.substring(str.indexOf("<script>") + 8, str.indexOf("</script>"));
        String styleStr = str.substring(str.indexOf("<style scoped>") + 14, str.indexOf("</style>"));
        System.out.println("templateStr = " + templateStr);
        System.out.println("scriptStr = " + scriptStr);
        System.out.println("styleStr = " + styleStr);


    }
}
