<template>
    <div class="app-container">
        <div class="filter-container">
            <el-input v-model="listQuery.name" placeholder="名称" style="width: 200px;" class="filter-item"
                      @keyup.enter.native="handleFilter"/>
            <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-search"
                       @click="handleFilter">
                查询
            </el-button>
            <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-circle-close">
                清理
            </el-button>
            <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit"
                       @click="handleCreate">
                新增
            </el-button>
        </div>

        <el-table
                :key="tableKey"
                v-loading="listLoading"
                :data="list"
                border
                fit
                highlight-current-row
                style="width: 100%;"
        >
            <el-table-column label="序号" width="50" align="center" type="index" :index="indexMethod"></el-table-column>

            <#if columns??>
                <#list columns as column>
                    <el-table-column label="${column.remarks}" width="100" align="center">
                        <template slot-scope="{row}">
                            <span>{{ row.${column.varName} }}</span>
                        </template>
                    </el-table-column>
                </#list>
            </#if>

            <el-table-column label="操作" fixed="right" align="center" width="280" class-name="small-padding fixed-width">
                <template slot-scope="{row,$index}">
                    <el-button type="primary" size="mini" @click="handleUpdate(row)">
                        编辑
                    </el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(row,$index)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total>0" :total="total" :page.sync="listQuery.current" :limit.sync="listQuery.size"
                    @pagination="pageList"/>

        <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
            <el-form ref="dataForm" :rules="rules" :model="entity" label-position="left" label-width="100px"
                     style="width: 400px; margin-left:50px;">
                <#if columns??>
                    <#list columns as column>
                        <el-form-item label="${column.remarks}" prop="name">
                            <el-input v-model="entity.${column.varName}" placeholder="请填写${column.remarks}"/>
                        </el-form-item>
                    </#list>
                </#if>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">
                    关闭
                </el-button>
                <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
                    提交
                </el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import { add${className}, del${className}, page${className}, find${className}, get${className}, put${className} } from '@/api/${module!}/${className}API'
    import waves from '@/directive/waves'
    import Pagination from '@/components/Pagination'

    export default {
        name: 'ComplexTable',
        components: { Pagination },
        directives: { waves },
        filters: {},
        data() {
            return {
                tableKey: 0,
                list: [],
                total: 0,
                listLoading: true,
                listQuery: {
                    current: 1,
                    size: 10,
                    enable: undefined,
                    account: undefined,
                    name: undefined,
                    phone: undefined
                },
                entity: {
                    id: '',
                    parentId: '',
                    name: '',
                    sort: '',
                    enable: ''
                },
                dialogFormVisible: false,
                dialogStatus: '',
                textMap: {
                    update: '编辑',
                    create: '新增'
                },
                rules: {
                    name: [{ required: true, message: '请填写名称', trigger: 'blur' }]
                },
                downloadLoading: false,
            }
        },
        created() {
            this.pageList()
        },
        methods: {
            pageList() {
                this.listLoading = true
                page${className}(this.listQuery).then(response => {
                    this.list = response.data.records
                    this.total = response.data.total
                    setTimeout(() => {
                    this.listLoading = false
                }, 1.5 * 1000)
            })
            },
            handleFilter() {
                this.listQuery.current = 1
                this.pageList()
            },
            handleModifyStatus(row, status) {
                this.$message({
                    message: '操作Success',
                    type: 'success'
                })
                row.status = status
            },
            handleCreate() {
                this.entity = {}
                this.dialogStatus = 'create'
                this.dialogFormVisible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].clearValidate()
                })
            },
            createData() {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        console.info(this.entity)
                        add${className}(this.entity).then(() => {
                            this.list.unshift(this.entity)
                            this.dialogFormVisible = false
                            this.$notify({
                                title: '操作',
                                message: '创建成功',
                                type: 'success',
                                duration: 2000
                            })
                            this.pageList(this.current)
                        })
                    }
                })
            },
            handleUpdate(row) {
                this.entity = Object.assign({}, row)
                this.dialogStatus = 'update'
                this.dialogFormVisible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].clearValidate()
                })
            },
            updateData() {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        const data = Object.assign({}, this.entity)
                        put${className}(data).then(() => {
                            const index = this.list.findIndex(v => v.id === this.entity.id)
                        this.list.splice(index, 1, this.entity)
                        this.dialogFormVisible = false
                        this.$notify({
                            title: '操作',
                            message: 'Update Successfully',
                            type: 'success',
                            duration: 2000
                        })
                        this.pageList(this.current)
                    })
                    }
                })
            },
            handleDelete(row, index) {
                this.$confirm('确认是否删除【' + row.name + '】', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function() {
                    return del${className}(row.id)
                }).then(data => {
                    this.list.splice(index, 1)
                    this.$notify({
                        title: '操作',
                        message: '删除成功',
                        type: 'success',
                        duration: 2000
                    })
                    this.pageList(this.current)
                }).catch(function(err) {
                })
            },
            indexMethod(index) {
                return index * 1 + 1
            },
            clearDataForm() {
                this.listQuery = {}
                this.listQuery.current = 1
                this.listQuery.size = 10
            }
        }
    }
</script>
